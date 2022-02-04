package com.blog.pessoal.blogpessoal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogpessoalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogpessoalApplication.class, args);
		
		System.out.println("\n Seu Blog Pessoal agora está online!");
		
	}
}
