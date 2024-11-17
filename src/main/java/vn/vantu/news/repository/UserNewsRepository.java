package vn.vantu.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import vn.vantu.news.domain.UserNews;

@Repository
@Transactional
public interface UserNewsRepository extends JpaRepository<UserNews, Long>{
	boolean existsByUserIdAndNewsId(long userId, long newsId);
	
	UserNews save(UserNews userNews);
	
	void deleteByUserIdAndNewsId(long userId, long newsId);
	
	List<UserNews> findAllByUserId(long userId);
}
