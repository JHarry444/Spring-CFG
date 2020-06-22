package com.qa.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringExampleApplication {

	public static void main(String[] args) {
		ApplicationContext beanBag = SpringApplication.run(SpringExampleApplication.class, args);
		
		String message = new String("Hello, World!");
		System.out.println("Mine: " + message);
		
		String messageBean = beanBag.getBean("message", String.class);
		
		System.out.println("Spring Bean: " +  messageBean);
		System.out.println(beanBag.getBean("message", String.class));
		System.out.println(beanBag.getBean("message", String.class));
		System.out.println(beanBag.getBean("message", String.class));
		
		
		System.out.println(beanBag.getBean("message2", String.class));
	
//		List<String> beans = new ArrayList<>();	
//		AppConfig config = new AppConfig();
//		beans.add(config.message());
//		beans.add(config.message());
//		beans.add(config.message2());
//		System.out.println(beans);
	
	}
	
}
