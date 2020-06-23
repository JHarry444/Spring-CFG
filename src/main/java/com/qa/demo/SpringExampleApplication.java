package com.qa.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.qa.demo.config.AppConfig;
import com.qa.demo.persistence.domain.Flower;
import com.qa.demo.persistence.repo.FlowerRepo;
import com.qa.demo.service.FlowerService;

@SpringBootApplication
public class SpringExampleApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(SpringExampleApplication.class, args);
		
		FlowerService service = beanBag.getBean(FlowerService.class);
		System.out.println(service);
		String message = new String("Hello, World!");
		System.out.println("Mine: " + message);
		
		String messageBean = beanBag.getBean("message", String.class);
		
		System.out.println("Spring Bean: " +  messageBean);
		System.out.println(beanBag.getBean("message", String.class));
		System.out.println(beanBag.getBean("message", String.class));
		System.out.println(beanBag.getBean("message", String.class));
		
		
		System.out.println(beanBag.getBean("message2", String.class));
	
		List<String> beans = new ArrayList<>();	
		AppConfig config = new AppConfig();
		beans.add(config.message());
		beans.add(config.message2(config.message()));
		System.out.println(beans);
		
		System.out.println(beanBag.getBean(AppConfig.class));
		
		FlowerRepo flowerDAO = beanBag.getBean(FlowerRepo.class);
		flowerDAO.save(new Flower("rose", 15, "red", 44.94, false));
		System.out.println(flowerDAO.findByType("rose"));
	}
	
}
