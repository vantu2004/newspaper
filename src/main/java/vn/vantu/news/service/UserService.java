package vn.vantu.news.service;

import java.util.List;

import org.springframework.stereotype.Service;

import vn.vantu.news.domain.Role;
import vn.vantu.news.domain.User;
import vn.vantu.news.domain.dto.RegisterDTO;
import vn.vantu.news.repository.RoleRepository;
import vn.vantu.news.repository.UserRepository;

@Service
public class UserService {

	// Dependency injection
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserService(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	public void handleSaveUser(User user) {
		this.userRepository.save(user);
	}

	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

//	public List<User> getAllUserByEmail(String email) {
//		return this.userRepository.findByOneEmail(email);
//	}

	public User getInfoUserById(long id) {
		return this.userRepository.findById(id);
	}

	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

	public Role getRoleByName(String name) {
		return this.roleRepository.findByName(name);
	}

	// chuyển DTO thành user bằng cách code thủ công, hoặc có thể dùng công cụ
	// mapstruct
	public User registerDTOtoUser(RegisterDTO registerDTO) {
		User user = new User();

		user.setFullName(registerDTO.getFirstName() + " " + registerDTO.getLastName());
		user.setEmail(registerDTO.getEmail());
		user.setPassword(registerDTO.getPassword());

		return user;
	}

	public boolean checkEmailExist(String email) {
		return this.userRepository.existsByEmail(email);
	}
	
	public User getUserByEmail(String email) {
		return this.userRepository.findByEmail(email);
	}
}
