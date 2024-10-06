package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.repository.userbooks.UserBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final UserBooksRepository userBooksRepository;
    public String home(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null && principal.getAttribute("login") != null) {
            return "home";
        } else {
            return "index";
        }
    }
}
