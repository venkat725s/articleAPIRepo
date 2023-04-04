package com.abc.article.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagResponse {
    private String tag;
    private int count;
    @JsonProperty("articles")
    private List<String> articles;

    @JsonProperty("related_tags")
    private Set<String> relatedTags;
}
