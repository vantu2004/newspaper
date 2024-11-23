package vn.vantu.news.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>, JpaSpecificationExecutor<News>{
	
	News save(News news);
	
	boolean existsByLink(String link);
	
	// Lấy bản ghi mới nhất dựa trên cột id
    News findTopByOrderByIdDesc();
    
    List<News> findByCategoryIdOrderByPubdateDescPubtimeDesc(long categoryId);
    
    News findById(long id);
    
    List<News> findAllByCategoryId(long categoryId);
    
    Page<News> findAllByCategoryId(Pageable page, long id);
    
    List<News> findAll(Specification<News> spec);
}
