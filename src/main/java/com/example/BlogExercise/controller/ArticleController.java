package com.example.BlogExercise.controller;

import com.example.BlogExercise.domain.Article;
import com.example.BlogExercise.service.ArticleService;
import org.apache.coyote.BadRequestException;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class ArticleController {

    Logger logger = Logger.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;


    @GetMapping("/articles")
    public List<Article> article() {
        logger.info("Get articles");
        List<Article> response = articleService.findAll();
        logger.info(response.toString());
        return response;

    }

    @PostMapping("/articles")
    public Article add(@RequestBody Article article){
        logger.info("Post articles");
        Article response = articleService.Add(article);
        logger.info(response.toString());
        return response;
    }

    @PutMapping("/articles/{id}")
    public Article update(@RequestBody Article article,@PathVariable Long id){
        logger.info("Put articles " + id);
        Article response = articleService.update(article,id);
        logger.info(response.toString());
        return response;
    }

    @GetMapping("/articles/find/{id}")
    public Optional<Article> findById(@PathVariable Long id){
        logger.info("Get article by id " + id);
        Optional<Article> response = articleService.findById(id);
        logger.info(response.toString());
        return response;

    }

    @GetMapping("/articles/findByTitle/{title}")
    public List<Article> findByTitle(@PathVariable String title){
        logger.info("Get article by Title " + title);
        List<Article> response = articleService.findByTitle(title);
        logger.info(response.toString());
        return response;

    }

    @GetMapping("/articles/findByAuthor/{authorName}")
    public List<Article> findByAuthorName(@PathVariable String authorName){
        logger.info("Get article by authorName " + authorName);
        List<Article> response =  articleService.findByAuthorName(authorName);
        logger.info(response.toString());
        return response;

    }

}
