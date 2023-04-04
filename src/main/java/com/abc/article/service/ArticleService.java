package com.abc.article.service;

import com.abc.article.exception.ArticleNotFoundException;
import com.abc.article.model.ArticleRequest;
import com.abc.article.model.ArticleResponse;
import com.abc.article.model.TagResponse;

import java.text.ParseException;

public interface ArticleService {

    public ArticleResponse createArticle(ArticleRequest articleRequest);
    public ArticleResponse getById(Long id) throws ArticleNotFoundException;

    public TagResponse getByTagAndDate(String tag, String date) throws ArticleNotFoundException, ParseException;
}
