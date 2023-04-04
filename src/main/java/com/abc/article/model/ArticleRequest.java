package com.abc.article.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleRequest {

    private Long id;
    @NotEmpty
    private String title;
    @NotNull
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
    @NotEmpty
    private String body;
    @NotEmpty
    private List<String> tags;

}
