package vn.vantu.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{
	
	News save(News news);
	
	boolean existsByLink(String link);
	
	// Lấy bản ghi mới nhất dựa trên cột id
    News findTopByOrderByIdDesc();
}
