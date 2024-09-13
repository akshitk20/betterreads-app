package com.springboot.betterreads_app.model.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SearchBookResult {
    private String key;
    private String title;
    private List<String> author_names;
    private String cover_i;
    private int first_publish_year;
}
