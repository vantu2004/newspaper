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
import vn.vantu.news.service.NewsService;

@Controller
public class ItemController {
	
	private final NewsService newsService;
	
	public ItemController(NewsService newsService) {
		this.newsService = newsService;
	}
	
	@GetMapping("/listNews/{id}")
	private String getListNews(Model model,  @PathVariable long id) {
		ListNews listNews = this.newsService.getAllNews();
		List<News> listNewsOneCategory = this.newsService.getOneListNews(id);

		model.addAttribute("listNews", listNews);
		model.addAttribute("listNewsOneCategory", listNewsOneCategory);
		
		return "client/news/ListNews";
	}
	
	@GetMapping("/detail-news/{id}")
	private String getDetailNews(Model model,  @PathVariable long id) {
		ListNews listNews = this.newsService.getAllNews();
		
		News news = this.newsService.getDetailNews(id);
		
		model.addAttribute("listNews", listNews);
		model.addAttribute("news", news);
		
		return "client/news/DetailNews";
	}
	
	@PostMapping("/follow-news/{id}")
	public String followNews(@PathVariable long id, HttpServletRequest request) {
		// kiểm tra đã có session nào chưa, nếu chưa thì thay vì tạo mới thì nó truyền
		// vào false và trả về null
		HttpSession session = request.getSession(false);

		long newsId = id;
		// mặc định đang là object nên phải ép
		String email = (String) session.getAttribute("email");
		this.newsService.handleFollowNews(email, newsId, session);

		return "redirect:/detail-news/" + newsId;
	}

//	@GetMapping("/cart")
//	public String getCartPage(Model model, HttpServletRequest request) {
//		User currentUser = new User();
//
//		HttpSession session = request.getSession(false);
//		long id = (long) session.getAttribute("id");
//		currentUser.setId(id);
//
//		// phải lấy id từ session rồi gán cho currentUser để có thể join bảng
//		// mục đích truyền currentUser là để getCartByUser lấy currentUser join vs Cart
//		// và trả về cart
//		Cart cart = this.productService.getCartByUser(currentUser);
//		// khi gọi getCartDetail thì cart tự join với cartDetail theo id
//		// phải check điều kiện vì khi cart rỗng thì ko thể getCartDetail -> lỗi
//		List<CartDetail> cartDetails = cart == null ? new ArrayList<CartDetail>() : cart.getCartDetails();
//
//		double totalPrice = 0;
//		for (var cartDetail : cartDetails) {
//			totalPrice += cartDetail.getPrice() * cartDetail.getQuantity();
//		}
//
//		model.addAttribute("cartDetails", cartDetails);
//		model.addAttribute("totalPrice", totalPrice);
//
//		// thuộc tính path cần xuất phát từ 1 đối tượng gốc nên phải truyền cart, nếu
//		// dùng list cartDetails thì Cart.jsp ko hiểu
//		/*
//		 * Khi bạn sử dụng path="cartDetails[${status.index}].id", Spring cần biết
//		 * cartDetails thuộc đối tượng nào. Do đó, bạn phải cung cấp đối tượng bao
//		 * (trong trường hợp này là Cart) thông qua modelAttribute.
//		 */
//		model.addAttribute("cart", cart);
//
//		return "client/cart/Cart";
//	}
//
//	@PostMapping("/delete-cart-product/{id}")
//	public String deleteCartDetail(@PathVariable long id, HttpServletRequest request) {
//		HttpSession session = request.getSession(false);
//		long cartDetailId = id;
//		this.productService.handleRemoveCartDetail(cartDetailId, session);
//
//		return "redirect:/cart";
//	}
}
