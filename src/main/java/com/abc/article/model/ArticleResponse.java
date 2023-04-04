package com.abc.article.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    private Long id;
    private String title;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    private String body;
    private List<String> tags;
}
