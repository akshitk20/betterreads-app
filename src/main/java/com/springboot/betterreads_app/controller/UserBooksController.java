package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.model.book.Book;
import com.springboot.betterreads_app.model.user.BooksByUser;
import com.springboot.betterreads_app.model.userbooks.UserBooks;
import com.springboot.betterreads_app.model.userbooks.UserBooksPrimaryKey;
import com.springboot.betterreads_app.repository.book.BookRepository;
import com.springboot.betterreads_app.repository.user.BooksByUserRepository;
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
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserBooksController {

    private final UserBooksRepository userBooksRepository;

    private final BooksByUserRepository booksByUserRepository;

    private final BookRepository bookRepository;

    @PostMapping("/addUserBook")
    public ModelAndView addBookForUser(@RequestBody MultiValueMap<String, String> formData,
                                       @AuthenticationPrincipal OAuth2User principal) {
        if (principal == null || principal.getAttribute("login") == null) {
            return null;
        }
        System.out.println(formData);
        System.out.println(principal);
        String bookId = formData.getFirst("bookId");
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (!optionalBook.isPresent()) {
            return new ModelAndView("redirect:/");
        }
        Book book = optionalBook.get();

        UserBooks userBooks = new UserBooks();
        UserBooksPrimaryKey key = new UserBooksPrimaryKey();
        String userId = principal.getAttribute("login");
        key.setUserId(userId);
        key.setBookId(bookId);
        userBooks.setKey(key);
        userBooks.setStartedDate(LocalDate.parse(Objects.requireNonNull(formData.getFirst("startDate"))));
        userBooks.setCompletedDate(LocalDate.parse(Objects.requireNonNull(formData.getFirst("completedDate"))));
        userBooks.setRating(Integer.parseInt(Objects.requireNonNull(formData.getFirst("rating"))));
        userBooks.setReadingStatus(formData.getFirst("readingStatus"));

        userBooksRepository.save(userBooks);

        BooksByUser booksByUser = new BooksByUser();
        booksByUser.setBookId(bookId);
        booksByUser.setId(userId);
        booksByUser.setReadingStatus(formData.getFirst("readingStatus"));
        booksByUser.setBookName(book.getName());
        booksByUser.setAuthorNames(book.getAuthorNames());
        booksByUser.setCoverIds(book.getCoverIds());
        booksByUser.setRating(Integer.parseInt(Objects.requireNonNull(formData.getFirst("rating"))));

        booksByUserRepository.save(booksByUser);

        return new ModelAndView("redirect:/book/" + bookId);
    }
}
