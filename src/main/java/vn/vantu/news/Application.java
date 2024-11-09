package vn.vantu.news;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
//	@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
//		ApplicationContext getBeans = SpringApplication.run(Application.class, args);
//		for(String s: getBeans.getBeanDefinitionNames())
//		{
//			System.out.println(s);
//		}
	}

}
