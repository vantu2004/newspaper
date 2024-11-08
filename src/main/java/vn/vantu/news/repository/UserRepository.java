package vn.vantu.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User save(User user);

//	List<User> findByEmail(String email);

	User findById(long id);

	void deleteById(long id);

	boolean existsByEmail(String email);
	
	User findByEmail(String email);
}
