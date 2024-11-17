package vn.vantu.news.controller.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.vantu.news.domain.ListNews;
import vn.vantu.news.domain.News;
import vn.vantu.news.domain.UserNews;
import vn.vantu.news.service.NewsService;
import vn.vantu.news.service.UserNewsService;

@Controller
public class ItemController {

	private final NewsService newsService;
	private final UserNewsService userNewsService;

	public ItemController(NewsService newsService, UserNewsService userNewsService) {
		this.newsService = newsService;
		this.userNewsService = userNewsService;
	}

	@GetMapping("/listNews/{id}")
	private String getListNews(Model model, @PathVariable long id) {
		ListNews listNews = this.newsService.getAllNews();
		List<News> listNewsOneCategory = this.newsService.getOneListNews(id);

		model.addAttribute("listNews", listNews);
		model.addAttribute("listNewsOneCategory", listNewsOneCategory);

		return "client/news/ListNews";
	}

	@GetMapping("/detail-news/{id}")
	private String getDetailNews(Model model, @PathVariable long id, HttpServletRequest request) {
		ListNews listNews = this.newsService.getAllNews();

		News news = this.newsService.getDetailNews(id);

		model.addAttribute("listNews", listNews);
		model.addAttribute("news", news);

		HttpSession session = request.getSession(false);

		long newsId = id;
		
		if (session != null && session.getAttribute("id") != null) {
		    long userId = (long) session.getAttribute("id");

			boolean checkExistUserNews = this.userNewsService.checkExistUserNews(userId, newsId);

			model.addAttribute("checkExistUserNews", checkExistUserNews);
		}

		return "client/news/DetailNews";
	}

	@PostMapping("/follow-news/{id}")
	public String followNews(Model model, @PathVariable long id, HttpServletRequest request) {
		// kiểm tra đã có session nào chưa, nếu chưa thì thay vì tạo mới thì nó truyền
		// vào false và trả về null
		HttpSession session = request.getSession(false);

		long newsId = id;
		// mặc định đang là object nên phải ép
		String email = (String) session.getAttribute("email");

		this.newsService.handleFollowNews(email, newsId, session);

		return "redirect:/detail-news/" + newsId;
	}
	
	@PostMapping("/unfollow-news/{id}")
	public String unfollowNews(Model model, @PathVariable long id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		long newsId = id;
		long userId = (long) session.getAttribute("id");

		this.newsService.handleUnfollowNews(userId, newsId);
		
		return "redirect:/detail-news/" + newsId;
	}

	@GetMapping("/follow")
	public String getFollowNewsPage(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("id");
		
		List<UserNews> userNews = this.userNewsService.getAllFollowNews(userId);
		List<News> listFollowNews = this.newsService.getAllFollowNews(userNews);
	
		model.addAttribute("listFollowNews", listFollowNews);

		return "client/news/FollowNews";
	}

	@PostMapping("/unfollow-news-followPage/{id}")
	public String unfollowNewsFollowPage(Model model, @PathVariable long id, HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		long newsId = id;
		long userId = (long) session.getAttribute("id");

		this.newsService.handleUnfollowNews(userId, newsId);
		
		return "redirect:/follow";
	}
}
