package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.model.book.Book;
import com.springboot.betterreads_app.repository.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";

    @GetMapping(value = "/book/{bookId}")
    public String getBook(@PathVariable String bookId, Model model) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            String imageUrl;
            if (null != book.getCoverIds() && !book.getCoverIds().isEmpty()) {
                imageUrl = COVER_IMAGE_ROOT + book.getCoverIds().get(0) + "-L.jpg";
            } else {
                imageUrl = "/images/no-image.png";
            }
            model.addAttribute("coverImage", imageUrl);
            model.addAttribute("book", book);
            return "book";
        }

        return "book-not-found";
    }

}
