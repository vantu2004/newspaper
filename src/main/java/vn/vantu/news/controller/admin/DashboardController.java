package vn.vantu.news.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
	
	@GetMapping("/admin")
	public String getDashBoard()
	{
		return "admin/dashboard/DashBoard";
	}
}
