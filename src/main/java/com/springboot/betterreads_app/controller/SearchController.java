package com.springboot.betterreads_app.controller;

import com.springboot.betterreads_app.model.response.SearchBookResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import com.springboot.betterreads_app.model.response.SearchResult;
import reactor.core.publisher.Mono;

import java.util.List;

@Controller
public class SearchController {

    private final WebClient webClient;

    private final String COVER_IMAGE_ROOT = "http://covers.openlibrary.org/b/id/";


    public SearchController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024))
                        .build())
                .baseUrl("https://openlibrary.org/search.json").build();
    }

    @GetMapping(value = "/book/search")
    public String getSearchResults(@RequestParam String query, Model model) {
        Mono<SearchResult> resultMono = this.webClient.get()
                .uri("?q={query}", query)
                .retrieve()
                .bodyToMono(SearchResult.class);
        SearchResult searchResult = resultMono.block();
        List<SearchBookResult> books = searchResult.getDocs()
                .stream()
                .limit(10)
                .peek(bookResult -> {
                    bookResult.setKey(bookResult.getKey().replace("/works/",""));
                    String coverIds = bookResult.getCover_i();
                    if (StringUtils.hasLength(coverIds)) {
                        coverIds = COVER_IMAGE_ROOT + coverIds + "-M.jpg";
                    } else {
                        coverIds = "/images/no-image.png";
                    }
                    bookResult.setCover_i(coverIds);
                })
                .toList();
        model.addAttribute("searchResult", books);

        return "search";
    }
}
