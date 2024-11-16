package vn.vantu.news.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import vn.vantu.news.domain.Category;
import vn.vantu.news.domain.News;

@Service
public class UseJsoupGetNews {

	private final NewsService newsService;
	private final CategoryService categoryRepository;

	public UseJsoupGetNews(NewsService newsService, CategoryService categoryRepository) {
		this.newsService = newsService;
		this.categoryRepository = categoryRepository;
	}

	public void LoadNewsFromRSS() {
		GetInfoFromRSS("https://vnexpress.net/rss/the-gioi.rss", "Thế Giới");
		GetInfoFromRSS("https://vnexpress.net/rss/thoi-su.rss", "Thời Sự");
		GetInfoFromRSS("https://vnexpress.net/rss/kinh-doanh.rss", "Kinh Doanh");
		GetInfoFromRSS("https://vnexpress.net/rss/startup.rss", "Startup");
		GetInfoFromRSS("https://vnexpress.net/rss/the-thao.rss", "Thể Thao");
		GetInfoFromRSS("https://vnexpress.net/rss/giai-tri.rss", "Giải Trí");
		GetInfoFromRSS("https://vnexpress.net/rss/phap-luat.rss", "Pháp Luật");
		GetInfoFromRSS("https://vnexpress.net/rss/giao-duc.rss", "Giáo Dục");
		GetInfoFromRSS("https://vnexpress.net/rss/suc-khoe.rss", "Sức Khỏe");
		GetInfoFromRSS("https://vnexpress.net/rss/gia-dinh.rss", "Đời Sống");
		GetInfoFromRSS("https://vnexpress.net/rss/du-lich.rss", "Du Lịch");
		GetInfoFromRSS("https://vnexpress.net/rss/khoa-hoc.rss", "Khoa Học");
		GetInfoFromRSS("https://vnexpress.net/rss/so-hoa.rss", "Số Hóa");
		GetInfoFromRSS("https://vnexpress.net/rss/oto-xe-may.rss", "Xe");
		GetInfoFromRSS("https://vnexpress.net/rss/y-kien.rss", "Ý Kiến");
		GetInfoFromRSS("https://vnexpress.net/rss/tam-su.rss", "Tâm Sự");
	}

	private void GetInfoFromRSS(String url, String category) {
		try {
			Document doc = Jsoup.connect(url).get();

			Elements items = doc.select("item");

			for (Element item : items) {
				String title = item.select("title").text();
				if (title.isEmpty())
					continue;

				String pubDate = item.select("pubDate").text();
				if (pubDate.isEmpty())
					continue;

				String link = item.select("link").text();
				if (link.isEmpty() || this.newsService.checkLinkExist(link))
					continue;

				// Xử lý định dạng cho pubdate
				// Tách pubDate thành ngày và giờ
				DateTime dateTime = parsePubDate(pubDate);
				LocalDate formattedDate = dateTime.getDate();
				LocalTime formattedTime = dateTime.getTime();

				// Lấy nội dung trong thẻ <description>
				Element description = item.selectFirst("description");
				if (description == null)
					continue;

				// Parse nội dung bên trong CDATA
				Document descriptionDoc = Jsoup.parse(description.text());

				// Kiểm tra xem thẻ <img> có tồn tại không
				String imgSrc = descriptionDoc.selectFirst("img") != null
						? descriptionDoc.selectFirst("img").attr("src")
						: "";

				// Lấy phần tóm tắt nội dung bài viết
				String summary = descriptionDoc.text().replaceAll(".*?</a>", "").trim();

				Content_Author content_author = crawlDetailNews(link);
				if (content_author != null) {
					News news = new News();
					news.setTitle(title);
					news.setCategory(this.categoryRepository.getCategoryByName(category));
					news.setPubdate(formattedDate);
					news.setPubtime(formattedTime);
					news.setLink(link);
					news.setImage(imgSrc);
					news.setSummary(summary);
					news.setContent(content_author.getPara());
					news.setAuthor(content_author.getAuthor());

					this.newsService.handleSaveNews(news);

					printNewsInfo(news);
				}
			}
		} catch (IOException e) {
			System.out.println("Error fetching the RSS feed: " + e.getMessage());
		}
	}

	private void printNewsInfo(News news) {
		System.out.println("Title: " + news.getTitle());
		System.out.println("Category: " + news.getCategory().getNameCategory());
		System.out.println("Publish Date: " + news.getPubdate());
		System.out.println("Publish Time: " + news.getPubtime());
		System.out.println("Link: " + news.getLink());
		System.out.println("Image: " + news.getImage());
		System.out.println("Summary: " + news.getSummary());
		System.out.println("Content: " + news.getContent());
		System.out.println("Author: " + news.getAuthor());
		System.out.println("------------------------------------------------------------");
	}

	public static DateTime parsePubDate(String pubDate) {
		DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z");

		try {
			// Phân tích chuỗi pubDate thành LocalDateTime
			LocalDateTime localDateTime = LocalDateTime.parse(pubDate, inputDateFormat);

			// Tách LocalDate và LocalTime từ LocalDateTime
			LocalDate localDate = localDateTime.toLocalDate();
			LocalTime localTime = localDateTime.toLocalTime();

			// Trả về đối tượng DateTime chứa LocalDate và LocalTime
			return new DateTime(localDate, localTime);
		} catch (Exception e) {
			System.out.println("Error parsing pubDate: " + pubDate);
			return new DateTime(null, null); // Trả về null nếu có lỗi
		}
	}

	public Content_Author crawlDetailNews(String url) {
		String para = "";
		String author = "";

		try {
			Document doc = Jsoup.connect(url).get();

			Element section = doc.select("html > body > section.section.page-detail.top-detail").first();
			if (section == null) {
				return null;
			}

			Element sidebar = section.select("div.sidebar-1").first();
			if (sidebar == null) {
				return null;
			}

			Element description = sidebar.select("p.description").first();
			if (description != null) {
				para += "<p>" + description.text() + "</p>";
			}

			Elements paragraphs = sidebar.select("p.Normal");
			if (paragraphs.isEmpty()) {
				return null;
			}

			for (int i = 0; i < paragraphs.size(); i++) {
				Element paragraph = paragraphs.get(i);

				if (i == paragraphs.size() - 1) {
					author = paragraph.text();
					break;
				}

				para += "<p>" + paragraph.text() + "</p>";
			}

			if (para.length() >= 500) {
				return new Content_Author(para, author);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}

class DateTime {
	private LocalDate date;
	private LocalTime time;

	public DateTime(LocalDate date, LocalTime time) {
		this.date = date;
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public LocalTime getTime() {
		return time;
	}
}

class Content_Author {
	private String para;
	private String author;

	public Content_Author(String para, String author) {
		this.para = para;
		this.author = author;
	}

	public String getPara() {
		return para;
	}

	public String getAuthor() {
		return author;
	}
}
