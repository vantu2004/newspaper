package vn.vantu.news.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.vantu.news.domain.ListNews;
import vn.vantu.news.domain.News;
import vn.vantu.news.domain.UserCategory;
import vn.vantu.news.domain.UserNews;
import vn.vantu.news.service.CollaborativeFilteringService;
import vn.vantu.news.service.NewsService;
import vn.vantu.news.service.UserCategoryService;
import vn.vantu.news.service.UserNewsService;

@Controller
public class ItemController {

	private final NewsService newsService;
	private final UserNewsService userNewsService;
	private final UserCategoryService userCategoryService;
	private final CollaborativeFilteringService collaborativeFilteringService;

	public ItemController(NewsService newsService, UserNewsService userNewsService,
			UserCategoryService userCategoryService, CollaborativeFilteringService collaborativeFilteringService) {
		this.newsService = newsService;
		this.userNewsService = userNewsService;
		this.userCategoryService = userCategoryService;
		this.collaborativeFilteringService = collaborativeFilteringService;
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

		long newsId = id;
		News news = this.newsService.getDetailNews(newsId);

		model.addAttribute("listNews", listNews);
		model.addAttribute("news", news);

		HttpSession session = request.getSession(false);

		if (session != null && session.getAttribute("id") != null) {
			long userId = (long) session.getAttribute("id");

			boolean checkExistUserNews = this.userNewsService.checkExistUserNews(userId, newsId);
			model.addAttribute("checkExistUserNews", checkExistUserNews);

			//	dùng cho rating, hiện sao, truyền UserCategory
			UserCategory us = this.userCategoryService.getUserCategory(userId, news.getCategory().getId());
			if (us != null) {
				model.addAttribute("userCategory", us);
			} else {
				model.addAttribute("userCategory", new UserCategory());
			}

			model.addAttribute("userId", userId);
			
			//	tạo ma trận dữ liệu thô từ table UserCategory
			double[][] matrix = this.collaborativeFilteringService.createMatrix();
			//	dùng thuật toán để lấy ra các danh mục đề xuất cho người dùng
			List<List<Integer>> recommend = this.collaborativeFilteringService.collaborativeFiltering(matrix);

			//	truyền vào view để lấy các bài báo và danh mục đề xuất
			List<Integer> recommendationCategory = recommend.get((int)userId -1);
			List<News> recommendationNews = this.newsService.handleRecommendNewsForUser(recommend, userId);	
			model.addAttribute("recommendationCategory", recommendationCategory);
			model.addAttribute("recommendationNews", recommendationNews);
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

	@PostMapping("/rating")
	public String submitRating(Model model, @ModelAttribute("userCategory") UserCategory userCategory,
			@RequestParam("newsId") long newsId) {

		UserCategory us = this.userCategoryService.getUserCategory(userCategory.getUser().getId(),
				userCategory.getCategory().getId());

		if (us != null) {
			us.setInteractionScore(userCategory.getInteractionScore());
			this.userCategoryService.handleSaveUserCategory(us);
		} else {
			this.userCategoryService.handleSaveUserCategory(userCategory);
		}

		return "redirect:/detail-news/" + newsId;
	}
}
