package vn.vantu.news.controller.client;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import vn.vantu.news.domain.User;
import vn.vantu.news.service.UploadImageService;
import vn.vantu.news.service.UserService;

@Controller
public class ClientController {
	private final UploadImageService uploadImageService;
	private final UserService userservice;

	public ClientController(UserService userservice, UploadImageService uploadImageService) {
		this.userservice = userservice;
		this.uploadImageService = uploadImageService;
	}

	@GetMapping("/client/user/update/{id}") // GET
	public String getUpdateUserPage(Model model, @PathVariable long id) {
		User currentUser = this.userservice.getInfoUserById(id);

		model.addAttribute("newUser", currentUser);
		model.addAttribute("avatar", currentUser.getAvatar());

		return "client/user/UpdateUser";
	}

	@PostMapping("/client/user/update")
	public String postUpdateUser(Model model, @ModelAttribute("newUser") User user,
			@RequestParam("imageFile") MultipartFile file) throws IOException {
		User currentUser = this.userservice.getInfoUserById(user.getId());

		// Đảm bảo file ko null để avatar hoặc là có trị hoặc là ko, giúp đảm bảo việc
		// xuất ảnh bên UpdateUser.jsp
		String avatar = null;
		if (file != null && !file.isEmpty()) {
			avatar = this.uploadImageService.handleSaveUploadFile(file, "avatar");
		}

		if (currentUser != null) {
			currentUser.setAddress(user.getAddress());
			currentUser.setFullName(user.getFullName());
			currentUser.setPhone(user.getPhone());
			if (avatar != null) {
				currentUser.setAvatar(avatar);
			}

			this.userservice.handleSaveUser(currentUser);
		}

		return "redirect:/";
	}
}
