package vn.vantu.news.service;

import org.springframework.stereotype.Service;

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
}
