package vn.vantu.news.service;

import org.springframework.stereotype.Service;

import vn.vantu.news.domain.UserNews;
import vn.vantu.news.repository.UserNewsRepository;

@Service
public class UserNewsService {
	private final UserNewsRepository userNewsRepository;
	
	public UserNewsService(UserNewsRepository userNewsRepository) {
		this.userNewsRepository = userNewsRepository;
	}
	
	public boolean checkExistUserNews(long userId, long newsId) {
		return this.userNewsRepository.existsByUserIdAndNewsId(userId, newsId);
	}
	
	public void handleSaveUserNews(UserNews userNews) {
		this.userNewsRepository.save(userNews);
	}
}
