package com.abc.article.service;

import com.abc.article.entity.Article;
import com.abc.article.entity.Tag;
import com.abc.article.exception.ArticleNotFoundException;
import com.abc.article.model.ArticleRequest;
import com.abc.article.model.ArticleResponse;
import com.abc.article.model.TagResponse;
import com.abc.article.repository.ArticleRepository;
import com.abc.article.repository.TagRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService{

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ArticleResponse createArticle(ArticleRequest articleRequest) {

        Article article = modelMapper.map(articleRequest, Article.class);
         Set<Tag> tagList = new HashSet<>();

        //Create and save tags
        for (final String tagName: articleRequest.getTags()) {
            Tag newTag = new Tag();
            newTag.setName(tagName);
            log.info("New tag={}",newTag);
            tagList.add(newTag);
        }
        //Response
        log.info("Article Id={}",article.getArticleId());
        article.setTags(tagList);
        Article articleRes = articleRepository.save(article);

        //Build Response
        return convertResponse(articleRes);
    }

    @Override
    public ArticleResponse getById(Long id) throws ArticleNotFoundException {

        Optional<Article> articleOptional = articleRepository.findById(id);
        if(articleOptional.isPresent()){
            //Build Response
            return convertResponse(articleOptional.get());
        }
        else {
            throw new ArticleNotFoundException("Article not found for Id: "+id);
        }
    }

    @Override
    public TagResponse getByTagAndDate(String tagName, String dateStr) throws ArticleNotFoundException, ParseException {

        log.info("Parsed Date={}"+dateStr);
        List<Article> articleList = articleRepository.findAllByCreatedDate(dateStr);
        int count = 0;

        TagResponse tagResponse = new TagResponse();
        tagResponse.setTag(tagName);
        tagResponse.setArticles(new ArrayList<>());
        tagResponse.setRelatedTags(new HashSet<>());
        boolean tagIsFound = false;


        for(Article article: articleList) {

            final Set<String> tags = article.getTags().stream().map(e -> e.getName()).collect(Collectors.toSet());

             for (String tagName1 : tags) {
                if (tagName1.equalsIgnoreCase(tagName)) {
                    tagIsFound = true;
                    break;
                }
            }
            if (tagIsFound) {
                for (String tagName1 : tags) {
                    if (tagName1.equalsIgnoreCase(tagName)) {
                        if(count<10){
                            tagResponse.getArticles().add(article.getArticleId().toString());
                        }
                        count++;
                    } else {
                        tagResponse.getRelatedTags().add(tagName1);
                    }
                }
            }
        }

        log.info("tagIsFound {}",tagIsFound);
        tagResponse.setCount(count);

        if(tagIsFound){
            return tagResponse;
        }
        return null;
    }

    private ArticleResponse convertResponse(final Article article){
        final ArticleResponse articleResponse = modelMapper.map(article, ArticleResponse.class);
        List<String> tagListResponse =  article.getTags().stream().map(e -> e.getName()).collect(Collectors.toList());
        articleResponse.setTags(tagListResponse);
        return articleResponse;
    }

}
