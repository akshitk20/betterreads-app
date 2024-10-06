package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.model.userbooks.UserBooks;
import com.springboot.betterreads_app.model.userbooks.UserBooksPrimaryKey;
import com.springboot.betterreads_app.repository.userbooks.UserBooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class UserBooksController {

    private final UserBooksRepository userBooksRepository;

    @PostMapping("/addUserBook")
    public ModelAndView addBookForUser(@RequestBody MultiValueMap<String, String> formData,
                                       @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null || principal.getAttribute("login") == null) {
            return null;
        }
        System.out.println(formData);
        System.out.println(principal);
        UserBooks userBooks = new UserBooks();
        UserBooksPrimaryKey key = new UserBooksPrimaryKey();
        key.setUserId(principal.getAttribute("login"));
        String bookId = formData.getFirst("bookId");
        key.setBookId(bookId);
        userBooks.setKey(key);
        userBooks.setStartedDate(LocalDate.parse(Objects.requireNonNull(formData.getFirst("startDate"))));
        userBooks.setCompletedDate(LocalDate.parse(Objects.requireNonNull(formData.getFirst("completedDate"))));
        userBooks.setRating(Integer.parseInt(Objects.requireNonNull(formData.getFirst("rating"))));
        userBooks.setReadingStatus(formData.getFirst("readingStatus"));

        userBooksRepository.save(userBooks);
        return new ModelAndView("redirect:/book/" + bookId);
    }
}
