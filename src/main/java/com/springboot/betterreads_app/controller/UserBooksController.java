package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.repository.userbooks.UserBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class UserBooksController {

    private final UserBooksRepository userBooksRepository;

    @PostMapping("/addUserBook")
    public String addBookForUser(@RequestBody MultiValueMap<String, String> formData,
                                 @AuthenticationPrincipal OAuth2User principal) {
        System.out.println(formData);
        System.out.println(principal);
        return "";
    }
}
