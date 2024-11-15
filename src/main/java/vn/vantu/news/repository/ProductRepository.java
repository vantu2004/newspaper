package vn.vantu.news.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product save(Product product);
	
	Product findById(long id);
	
	List<Product> findAll();
	
	void deleteById(long id);
}
