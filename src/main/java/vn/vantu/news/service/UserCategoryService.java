package vn.vantu.news.service;

import org.springframework.stereotype.Service;

import vn.vantu.news.domain.UserCategory;
import vn.vantu.news.repository.UserCategoryRepository;

@Service
public class UserCategoryService {
	private final UserCategoryRepository userCategoryRepository;

	public UserCategoryService(UserCategoryRepository userCategoryRepository) {
		this.userCategoryRepository = userCategoryRepository;
	}

	public void handleSaveUserCategory(UserCategory userCategory) {
		this.userCategoryRepository.save(userCategory);
	}

	public UserCategory getUserCategory(long userId, long categoryId) {
		return this.userCategoryRepository.findByUserIdAndCategoryId(userId, categoryId);
	}
}
