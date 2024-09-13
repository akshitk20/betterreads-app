package com.springboot.betterreads_app.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchResult {

    private int numFound;
    private List<SearchBookResult> docs;
}
