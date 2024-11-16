package vn.vantu.news.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import vn.vantu.news.domain.ListNews;
import vn.vantu.news.domain.News;
import vn.vantu.news.repository.NewsRepository;

@Service
public class NewsService {

	private final NewsRepository newsRepository;

	public NewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	public void handleSaveNews(News news) {
		this.newsRepository.save(news);
	}

	public boolean checkLinkExist(String link) {
		return this.newsRepository.existsByLink(link);
	}

	public long getTopIndexNews() {
		News news = this.newsRepository.findTopByOrderByIdDesc();
		return news == null ? 0 : news.getId();
	}

	public List<News> getOneListNews(long id)
	{
		return this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(id);
	}
	
	public ListNews getAllNews() {
		ListNews listNews = new ListNews();

		listNews.setTheGioi(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(1));
		listNews.setThoiSu(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(2));
		listNews.setKinhDoanh(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(3));
		listNews.setStartup(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(4));
		listNews.setTheThao(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(5));
		listNews.setGiaiTri(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(6));
		listNews.setPhapLuat(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(7));
		listNews.setGiaoDuc(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(8));
		listNews.setSucKhoe(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(9));
		listNews.setDoiSong(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(10));
		listNews.setDuLich(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(11));
		listNews.setKhoaHoc(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(12));
		listNews.setSoHoa(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(13));
		listNews.setXe(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(14));
		listNews.setyKien(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(15));
		listNews.setTamSu(this.newsRepository.findByCategoryIdOrderByPubdateDescPubtimeDesc(16));

		listNews.setTinMoi(mixNews(listNews));

		return listNews;
	}

	private List<News> mixNews(ListNews listNews) {
	    List<News> tinMoi = new ArrayList<>();
	    Method[] methods = ListNews.class.getDeclaredMethods();

	    for (Method method : methods) {
	        if (method.getName().startsWith("get")) {
	            try {
	                List<News> categoryNews = (List<News>) method.invoke(listNews);
	                tinMoi.addAll(getTopNews(categoryNews, 5));
	            } catch (Exception e) {
	                e.printStackTrace(); // Xử lý lỗi nếu cần
	            }
	        }
	    }

	    Collections.shuffle(tinMoi);
	    return tinMoi;
	}


	// Phương thức lấy n phần tử đầu tiên từ một danh sách
	private List<News> getTopNews(List<News> newsList, int limit) {
		return newsList.subList(0, Math.min(limit, newsList.size()));
	}
	
	public News getDetailNews(long id) {
		return this.newsRepository.findById(id);
	}
}
