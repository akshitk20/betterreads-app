package com.springboot.betterreads_app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserBooksController {

    @PostMapping("/addUserBook")
    public String addBookForUser(@AuthenticationPrincipal OAuth2User principal) {

    }
}
