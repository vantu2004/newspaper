package vn.vantu.news.controller.client;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import vn.vantu.news.domain.User;
import vn.vantu.news.domain.dto.ListNews;
import vn.vantu.news.domain.dto.RegisterDTO;
import vn.vantu.news.service.NewsService;
import vn.vantu.news.service.UserService;
import vn.vantu.news.service.Jsoup.UseJsoupGetNews;

@Controller
public class HomePageController {
	private final UserService userservice;
	private final PasswordEncoder passwordEncoder;
	private final UseJsoupGetNews useJsoupGetNews;
	private final NewsService newsService;

	public HomePageController(UserService userservice, PasswordEncoder passwordEncoder,
			UseJsoupGetNews useJsoupGetNews, NewsService newsService) {
		this.userservice = userservice;
		this.passwordEncoder = passwordEncoder;
		this.useJsoupGetNews = useJsoupGetNews;
		this.newsService = newsService;
	}

	@Scheduled(fixedDelay = 86400000)
	//@Scheduled(fixedDelay = 10000)
	public void loadNewsTask() {
	    this.useJsoupGetNews.LoadNewsFromRSS();
	}

	@GetMapping("/")
	private String getHomePage(Model model, HttpServletRequest request) {
		//this.useJsoupGetNews.LoadNewsFromRSS();

		ListNews listNews = newsService.getAllNews();
		model.addAttribute("listNews", listNews);

		return "client/homepage/HomePage";
	}

	@GetMapping("/register")
	private String getRegisterPage(Model model, @ModelAttribute("registerUser") RegisterDTO registerDTO) {

		return "client/auth/Register";
	}

	@PostMapping("/register")
	private String handleRegister(Model model, @ModelAttribute("registerUser") @Valid RegisterDTO registerDTO,
			BindingResult bidingresult) {

		// Validate thông tin tạo
		List<FieldError> errors = bidingresult.getFieldErrors();
		for (FieldError error : errors) {
			System.out.println(error.getField() + " - " + error.getDefaultMessage());
		}

		if (bidingresult.hasErrors()) {
			return "client/auth/Register";
		}

		User user = this.userservice.registerDTOtoUser(registerDTO);

		// Hash pass
		String hashPassword = this.passwordEncoder.encode(user.getPassword());

		user.setPassword(hashPassword);
		// phải setRole vì role đang tham chiếu đến Role, và 1 người dùng thì phải có
		// role
		// mặc dịnh người dùng là USER
		user.setRole(this.userservice.getRoleByName("USER"));

		// save
		this.userservice.handleSaveUser(user);

		return "redirect:/login";
	}

	@GetMapping("/login")
	private String getLoginPage(Model model, @ModelAttribute("registerUser") RegisterDTO registerDTO) {

		return "client/auth/Login";
	}

	@GetMapping("/access-deny")
	private String getDenyPage(Model model) {

		return "client/auth/Deny";
	}
}
