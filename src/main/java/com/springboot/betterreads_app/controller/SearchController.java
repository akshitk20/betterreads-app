package com.springboot.betterreads_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import com.springboot.betterreads_app.model.response.SearchResult;
import reactor.core.publisher.Mono;

@Controller
public class SearchController {

    private final WebClient webClient;

    public SearchController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://openlibrary.org/search.json").build();
    }

    public String getSearchResults(@RequestParam String query, Model model) {
        Mono<SearchResult> resultMono = this.webClient.get()
                .uri("?q={query}", query)
                .retrieve()
                .bodyToMono(SearchResult.class);
        SearchResult searchResult = resultMono.block();
        model.addAttribute("numFound", searchResult.getNumFound());

        return "search";
    }
}
