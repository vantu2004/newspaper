package vn.vantu.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.vantu.news.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByName (String name);
}
