package com.springboot.betterreads_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BetterreadsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterreadsAppApplication.class, args);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("login");
	}

}
