package vn.vantu.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.UserNews;

@Repository
public interface UserNewsRepository extends JpaRepository<UserNews, Long>{
	boolean existsByUserIdAndNewsId(long userId, long newsId);
	
	UserNews save(UserNews userNews);
}
