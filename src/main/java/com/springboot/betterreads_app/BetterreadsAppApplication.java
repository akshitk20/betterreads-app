package com.springboot.betterreads_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class BetterreadsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterreadsAppApplication.class, args);
	}

	@RequestMapping("/user")
	public String getUser(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getName();
	}

}
