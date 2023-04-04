package com.abc.article.controller;

import com.abc.article.exception.ArticleNotFoundException;
import com.abc.article.model.ArticleRequest;
import com.abc.article.model.ArticleResponse;
import com.abc.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/articles")
@Slf4j
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @PostMapping(produces="application/json",consumes="application/json")
    public ResponseEntity<ArticleResponse> createArticle(@RequestBody @Valid final ArticleRequest articleRequest){

        log.debug("Inside Create Article");
        ArticleResponse response = articleService.createArticle(articleRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(path="/{id}",produces="application/json")
    public ResponseEntity<ArticleResponse> getData(@PathVariable Long id) throws ArticleNotFoundException {

        log.debug("Requested Id {}",id);
        ArticleResponse response = articleService.getById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
