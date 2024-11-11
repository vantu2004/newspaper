package vn.vantu.news.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.vantu.news.domain.ListNews;
import vn.vantu.news.domain.News;
import vn.vantu.news.domain.Product;
import vn.vantu.news.service.NewsService;
import vn.vantu.news.service.ProductService;

@Controller
public class ItemController {
	
	private final NewsService newsService;
	
	public ItemController(NewsService newsService) {
		this.newsService = newsService;
	}

//	@GetMapping("/product/{id}")
//	private String getHomePage(Model model,  @PathVariable long id) {
//		Product product = this.productService.getInfoProductById(id);
//		model.addAttribute("product", product);
//		model.addAttribute("id", id);
//		
//		return "client/product/Detail";
//	}
	
	@GetMapping("/listNews/{id}")
	private String getListNews(Model model,  @PathVariable long id) {
		ListNews listNews = this.newsService.getAllNews();
		List<News> listNewsOneCategory = this.newsService.getOneListNews(id);

		model.addAttribute("listNews", listNews);
		model.addAttribute("listNewsOneCategory", listNewsOneCategory);
		
		return "client/news/ListNews";
	}
}
