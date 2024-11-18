package vn.vantu.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.UserCategory;

@Repository
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
	UserCategory save(UserCategory userCategory);

	UserCategory findByUserIdAndCategoryId(long userId, long categoryId);

	@Query(value = "SELECT category_id, user_id, interaction_score FROM user_category", nativeQuery = true)
	List<Object[]> findAllUserCategoryData();
}
