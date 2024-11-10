package vn.vantu.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	Category findByNameCategory(String nameCategory);
}
