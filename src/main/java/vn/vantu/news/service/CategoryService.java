package vn.vantu.news.service;

import org.springframework.stereotype.Service;

import vn.vantu.news.domain.Category;
import vn.vantu.news.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
	
	
	public Category getCategoryByName (String nameCategory) {
		return this.categoryRepository.findByNameCategory(nameCategory);
	}
}
