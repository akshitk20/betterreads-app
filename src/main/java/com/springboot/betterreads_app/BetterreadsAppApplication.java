package com.springboot.betterreads_app;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.betterreads_app.properties.DatastaxAstraProperties;

import java.nio.file.Paths;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(DatastaxAstraProperties.class)
public class BetterreadsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BetterreadsAppApplication.class, args);
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUser(@AuthenticationPrincipal OAuth2User principal) {
		System.out.println(principal);
		return principal.getAttribute("login");
	}

	@Bean
	public CqlSession cqlSession() {
		return CqlSession.builder()
				.withCloudSecureConnectBundle(Paths.get("src/main/resources/secure-connect.zip"))
				.withAuthCredentials("client-id", "client-secret")
				.withKeyspace("main")  // Specify your keyspace here
				.build();
	}

}
