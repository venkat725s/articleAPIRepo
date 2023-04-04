package com.abc.article.controller;

import com.abc.article.exception.ArticleNotFoundException;
import com.abc.article.model.TagResponse;
import com.abc.article.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping(path = "/tags")
@Slf4j
public class TagController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(path="/{tagName}/{date}",produces="application/json")
    public ResponseEntity<TagResponse> getData(@PathVariable String tagName, @PathVariable String date)
            throws ArticleNotFoundException, ParseException {

        log.info("Requested Tag {}",tagName);
        log.info("Requested Date {}",date);

        TagResponse response = articleService.getByTagAndDate(tagName,date);
        if(response==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
