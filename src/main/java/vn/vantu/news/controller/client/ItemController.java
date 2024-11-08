package vn.vantu.news.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.vantu.news.domain.Product;
import vn.vantu.news.service.ProductService;

@Controller
public class ItemController {
	
	private ProductService productService;
	public ItemController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/product/{id}")
	private String getHomePage(Model model,  @PathVariable long id) {
		Product product = this.productService.getInfoProductById(id);
		model.addAttribute("product", product);
		model.addAttribute("id", id);
		
		return "client/product/Detail";
	}
}
