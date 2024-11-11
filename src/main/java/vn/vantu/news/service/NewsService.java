package vn.vantu.news.service;

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
		return this.newsRepository.findByCategoryIdOrderByPubdateDesc(id);
	}
	
	public ListNews getAllNews() {
		ListNews listNews = new ListNews();

		listNews.setTheGioi(this.newsRepository.findByCategoryIdOrderByPubdateDesc(1));
		listNews.setThoiSu(this.newsRepository.findByCategoryIdOrderByPubdateDesc(2));
		listNews.setKinhDoanh(this.newsRepository.findByCategoryIdOrderByPubdateDesc(3));
		listNews.setStartup(this.newsRepository.findByCategoryIdOrderByPubdateDesc(4));
		listNews.setTheThao(this.newsRepository.findByCategoryIdOrderByPubdateDesc(5));
		listNews.setGiaiTri(this.newsRepository.findByCategoryIdOrderByPubdateDesc(6));
		listNews.setPhapLuat(this.newsRepository.findByCategoryIdOrderByPubdateDesc(7));
		listNews.setGiaoDuc(this.newsRepository.findByCategoryIdOrderByPubdateDesc(8));
		listNews.setSucKhoe(this.newsRepository.findByCategoryIdOrderByPubdateDesc(9));
		listNews.setDoiSong(this.newsRepository.findByCategoryIdOrderByPubdateDesc(10));
		listNews.setDuLich(this.newsRepository.findByCategoryIdOrderByPubdateDesc(11));
		listNews.setKhoaHoc(this.newsRepository.findByCategoryIdOrderByPubdateDesc(12));
		listNews.setSoHoa(this.newsRepository.findByCategoryIdOrderByPubdateDesc(13));
		listNews.setXe(this.newsRepository.findByCategoryIdOrderByPubdateDesc(14));
		listNews.setyKien(this.newsRepository.findByCategoryIdOrderByPubdateDesc(15));
		listNews.setTamSu(this.newsRepository.findByCategoryIdOrderByPubdateDesc(16));

		listNews.setTinMoi(mixNews(listNews));

		return listNews;
	}

	private List<News> mixNews(ListNews listNews) {
		List<News> tinMoi = new ArrayList<>();

		// Lấy 5 phần tử đầu tiên từ mỗi danh sách
		tinMoi.addAll(getTopNews(listNews.getTheGioi(), 5));
		tinMoi.addAll(getTopNews(listNews.getThoiSu(), 5));
		tinMoi.addAll(getTopNews(listNews.getKinhDoanh(), 5));
		tinMoi.addAll(getTopNews(listNews.getStartup(), 5));
		tinMoi.addAll(getTopNews(listNews.getTheThao(), 5));
		tinMoi.addAll(getTopNews(listNews.getGiaiTri(), 5));
		tinMoi.addAll(getTopNews(listNews.getPhapLuat(), 5));
		tinMoi.addAll(getTopNews(listNews.getGiaoDuc(), 5));
		tinMoi.addAll(getTopNews(listNews.getSucKhoe(), 5));
		tinMoi.addAll(getTopNews(listNews.getDoiSong(), 5));
		tinMoi.addAll(getTopNews(listNews.getDuLich(), 5));
		tinMoi.addAll(getTopNews(listNews.getKhoaHoc(), 5));
		tinMoi.addAll(getTopNews(listNews.getSoHoa(), 5));
		tinMoi.addAll(getTopNews(listNews.getXe(), 5));
		tinMoi.addAll(getTopNews(listNews.getyKien(), 5));
		tinMoi.addAll(getTopNews(listNews.getTamSu(), 5));

		// Trộn danh sách tổng hợp
		Collections.shuffle(tinMoi);
		return tinMoi;
	}

	// Phương thức lấy n phần tử đầu tiên từ một danh sách
	private List<News> getTopNews(List<News> newsList, int limit) {
		return newsList.subList(0, Math.min(limit, newsList.size()));
	}
}
