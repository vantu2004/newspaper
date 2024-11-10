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

import vn.vantu.news.domain.News;

@Service
public class UseJsoupGetNews {

	int count = 0;
	int count1 = 0;
	private final NewsService newsService;

	public UseJsoupGetNews(NewsService newsService) {
		this.newsService = newsService;
	}

	public void LoadNewsFromRSS() {
		long topIndexNews = this.newsService.getTopIndexNews();

		RSSLink(topIndexNews);

		System.out.println("oooooooooo000000000 " + topIndexNews + " 000000000oooooooooo");
	}

	private void RSSLink(long topIndexNews) {
		GetInfoFromRSS("https://vnexpress.net/rss/the-gioi.rss", "the_gioi", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/thoi-su.rss", "thoi_su", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/kinh-doanh.rss", "kinh_doanh", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/startup.rss", "startup", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/the-thao.rss", "the_thao", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/giai-tri.rss", "giai_tri", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/phap-luat.rss", "phap_luat", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/giao-duc.rss", "giao_duc", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/suc-khoe.rss", "suc_khoe", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/gia-dinh.rss", "doi_song", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/du-lich.rss", "du_lich", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/khoa-hoc.rss", "khoa_hoc", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/so-hoa.rss", "so_hoa", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/oto-xe-may.rss", "xe", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/y-kien.rss", "y_kien", topIndexNews);
		GetInfoFromRSS("https://vnexpress.net/rss/tam-su.rss", "tam_su", topIndexNews);

		System.out.println("oooooooooo000000000 " + count + " 000000000oooooooooo");
		count = 0;
		System.out.println("oooooooooo000000000 COUNT 1 = " + count1 + " 000000000oooooooooo");
		count1 = 0;
	}

	private boolean crawlDetailNews(String url) {
		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");

		String para = "";
		String author = "";

		try {
			Document doc = Jsoup.connect(url).get();

			Element section = doc.select("html > body > section.section.page-detail.top-detail").first();
			if (section == null) {
				return false;
			}

			Element sidebar = section.select("div.sidebar-1").first();
			if (sidebar == null) {
				return false;
			}

			Element description = sidebar.select("p.description").first();
			if (description == null) {
				return false;
			}

			para += description.text() + "\n";

			Elements paragraphs = sidebar.select("p.Normal");
			if (paragraphs.isEmpty()) {
				return false;
			}

			for (int i = 0; i < paragraphs.size(); i++) {
				Element paragraph = paragraphs.get(i);

				if (i == paragraphs.size() - 1) {
					author = paragraph.text();
					break;
				}

				para += paragraph.text() + "\n";
			}

			if (para.length() >= 500) {
				System.out.println("para: " + para + "\n" + "author: " + author);
				count1++;
				System.out.println(
						"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	private void GetInfoFromRSS(String url, String category, long topIndexNews) {
//		System.out.println("oooooooooo000000000 " + category + " 000000000oooooooooo");

		try {
			Document doc = Jsoup.connect(url).get();

			Elements items = doc.select("item");
			long numberOfItems = items.size();

			// Nếu lượng news trong db <= 500 thì mặc định cào hết, nhưng nếu trên rồi thì
			// chỉ cần cào mỗi link 10 bài
			if (topIndexNews <= 500) {
				topIndexNews = numberOfItems;
			} else {
				topIndexNews = 5;
			}

			long countLoop = 0;
			for (Element item : items) {

				// Giới hạn số lần crawl
				if (countLoop >= topIndexNews) {
					break;
				}
				countLoop++;

				String title = item.select("title").text();
				if (title.isEmpty())
					continue; 

				String pubDate = item.select("pubDate").text();
				if (pubDate.isEmpty())
					continue;

				String link = item.select("link").text();
				if (link.isEmpty())
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

//				// In kết quả
//				System.out.println("Title: " + title);
//				System.out.println("Publication Date: " + formattedDate);
//				System.out.println("Publication Time: " + formattedTime);
//				System.out.println("Link: " + link);
//				System.out.println("Image URL: " + imgSrc);
//				System.out.println("Summary: " + summary);
//				System.out.println("---------------------------------------------------");

				if (crawlDetailNews(link)) {
					News news = new News();
					news.setTitle(title);
					news.setCategory(category);
					news.setPubdate(formattedDate);
					news.setPubtime(formattedTime);
					news.setLink(link);
					news.setImage(imgSrc);
					news.setSummary(summary);

					handleSaveNews(news);

					count++;
				}
			}
		} catch (IOException e) {
			System.out.println("Error fetching the RSS feed: " + e.getMessage());
		}

//		 System.out.println("oooooooooo000000000 " + category + " 000000000oooooooooo");
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

	private void handleSaveNews(News news) {
		if (!this.newsService.checkLinkExist(news.getLink())) {
			this.newsService.handleSaveNews(news);
		}
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
