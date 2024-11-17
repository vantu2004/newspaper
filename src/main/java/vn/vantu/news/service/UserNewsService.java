package vn.vantu.news.service;

import java.util.List;

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
	
	public void handleDeleteUserNews(long userId, long newsId) {
		this.userNewsRepository.deleteByUserIdAndNewsId(userId, newsId);
	}
	
	public List<UserNews> getAllFollowNews(long userId) {
		return this.userNewsRepository.findAllByUserId(userId);
	}
}
