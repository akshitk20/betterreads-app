package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.model.user.BooksByUser;
import com.springboot.betterreads_app.repository.user.BooksByUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.query.CassandraPageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BooksByUserRepository booksByUserRepository;

    @GetMapping("/")
    public String home(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null && principal.getAttribute("login") != null) {
            String userId = principal.getAttribute("login");
            Slice<BooksByUser> booksSlice = booksByUserRepository.findAllById(userId, CassandraPageRequest.of(0, 50));
            List<BooksByUser> booksByUser = booksSlice.getContent();
            model.addAttribute("books", booksByUser);
            return "home";
        } else {
            return "index";
        }
    }
}
