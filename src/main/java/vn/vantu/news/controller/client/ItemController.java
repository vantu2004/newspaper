package vn.vantu.news.controller.client;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.vantu.news.domain.Comment;
import vn.vantu.news.domain.News;
import vn.vantu.news.domain.News_;
import vn.vantu.news.domain.User;
import vn.vantu.news.domain.UserCategory;
import vn.vantu.news.domain.UserNews;
import vn.vantu.news.domain.dto.ListNews;
import vn.vantu.news.service.CommentService;
import vn.vantu.news.service.NewsService;
import vn.vantu.news.service.UserCategoryService;
import vn.vantu.news.service.UserNewsService;
import vn.vantu.news.service.UserService;
import vn.vantu.news.service.CollaborativeFiltering.CollaborativeFilteringService;

@Controller
public class ItemController {

	private final UserService userService;
	private final NewsService newsService;
	private final UserNewsService userNewsService;
	private final UserCategoryService userCategoryService;
	private final CollaborativeFilteringService collaborativeFilteringService;
	private final CommentService commentService;

	public ItemController(UserService userService, NewsService newsService, UserNewsService userNewsService,
			UserCategoryService userCategoryService, CollaborativeFilteringService collaborativeFilteringService,
			CommentService commentService) {
		this.userService = userService;
		this.newsService = newsService;
		this.userNewsService = userNewsService;
		this.userCategoryService = userCategoryService;
		this.collaborativeFilteringService = collaborativeFilteringService;
		this.commentService = commentService;
	}

	@GetMapping("/listNews/{id}")
	private String getListNews(Model model, @PathVariable long id,
			@RequestParam("page") Optional<String> pageOptional) {

		ListNews listNews = this.newsService.getAllNews();
		model.addAttribute("listNews", listNews);

		int currentPage = 1;
		try {
			if (pageOptional.isPresent()) {
				currentPage = Integer.parseInt(pageOptional.get());
			}
		} catch (Exception ex) {

		}

		// trang bắt đầu khi dùng pageable là 0
		Pageable pageable = PageRequest.of(currentPage - 1, 16, Sort.by(News_.PUBDATE).descending());
		Page<News> pageNews = this.newsService.getOneListNews(pageable, id);
		List<News> news = pageNews.getContent().size() > 0 ? pageNews.getContent() : new ArrayList<>();

		// Số lượng trang hiển thị tối đa
		int maxDisplayPages = 5;
		// đảm bảo trang bắt đầu ko < 1
		int startPage = Math.max(1, currentPage - 2);
		// đảm bảo trang kết thúc luôn < totalPages
		int endPage = Math.min(startPage + maxDisplayPages - 1, pageNews.getTotalPages());

		// Điều chỉnh lại chỉ số cho trang bắt đầu
		if (endPage - startPage + 1 < maxDisplayPages) {
			startPage = Math.max(1, endPage - maxDisplayPages + 1);
		}

		model.addAttribute("categoryId", id);
		model.addAttribute("news", news);
		// Truyền startPage và endPage về view
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("currentPage", currentPage);

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

			// dùng cho rating, hiện sao, truyền UserCategory
			UserCategory us = this.userCategoryService.getUserCategory(userId, news.getCategory().getId());
			if (us != null) {
				model.addAttribute("userCategory", us);
			} else {
				model.addAttribute("userCategory", new UserCategory());
			}

			if (this.userCategoryService.checkExistUserId(userId)) {
				// tạo ma trận dữ liệu thô từ table UserCategory
				double[][] matrix = this.collaborativeFilteringService.createMatrix();

				// dùng thuật toán để lấy ra các danh mục đề xuất cho người dùng
				List<List<Integer>> recommend = this.collaborativeFilteringService.collaborativeFiltering(matrix);

				// truyền vào view để lấy các bài báo và danh mục đề xuất
				List<Integer> recommendationCategory = recommend.get((int) userId - 1);
				List<News> recommendationNews = this.newsService.handleRecommendNewsForUser(recommend, userId);
				model.addAttribute("recommendationCategory", recommendationCategory);
				model.addAttribute("recommendationNews", recommendationNews);
			} else {
				// vừa tạo người dùng mới nên chưa có dữ liệu -> cho xuất tin tức mới
				model.addAttribute("recommendationNews", listNews.getTinMoi());
			}

			model.addAttribute("userId", userId);

			// lấy tất cả comment trong bài báo
			List<Comment> listComment = this.commentService.getAllCommentByNewsId(newsId);
			model.addAttribute("listComment", listComment);
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

	@PostMapping("/search")
	public String searchNews(Model model, @RequestParam("keyword") String keyword) {

	    ListNews listNews = this.newsService.getAllNews();
	    model.addAttribute("listNews", listNews);

	    List<News> news;
	    if (keyword == null || keyword.trim().isEmpty()) {
	        // Lấy danh sách tất cả tin tức và giới hạn 50 phần tử
	        List<News> allNews = this.newsService.getAllNewsByKeyword(keyword);
	        int limit = Math.min(50, allNews.size()); // Đảm bảo không vượt quá kích thước danh sách
	        news = allNews.subList(0, limit);
	    } else {
	        // Tìm kiếm tin tức theo từ khóa
	        news = this.newsService.getAllNewsByKeyword(keyword);
	    }
	    model.addAttribute("news", news);

	    return "client/news/SearchNews";
	}


	@PostMapping("/comment")
	public String createComment(Model model, HttpServletRequest request, @RequestParam("newsId") long newsId,
			@RequestParam("comment") String comment) {
		HttpSession session = request.getSession(false);
		long userId = (long) session.getAttribute("id");
		User user = this.userService.getInfoUserById(userId);

		News news = this.newsService.getDetailNews(newsId);

		LocalDateTime now = LocalDateTime.now();

		if (comment != null && !comment.isEmpty()) {
			Comment cmt = new Comment();
			cmt.setContent(comment);
			cmt.setCommentDatetime(now);
			cmt.setUser(user);
			cmt.setNews(news);

			this.commentService.handleSaveComment(cmt);
		}

		return "redirect:/detail-news/" + newsId;
	}
}
