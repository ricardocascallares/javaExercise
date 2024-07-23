package com.example.BlogExercise.service;

import com.example.BlogExercise.domain.Article;
import com.example.BlogExercise.repository.ArticleRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    public ArticleRepository articleRepository;

    public List<Article> findAll( ) {
        Iterable<Article> articles = articleRepository.findAll();
            List<Article> articleList = new ArrayList<>();
            articles.forEach(articleList::add);
            return articleList;
    }

    public Article Add(Article article){
        return articleRepository.save(article);
    }

    public Article update(Article article,Long id){
        article.id=id;
        return articleRepository.save(article);
    }

    public Optional<Article> findById(Long id){
        return articleRepository.findById(id);
    }

    public List<Article> findByTitle(String title){
        return articleRepository.findByTitle(title);
    }

    public List<Article> findByAuthorName(String name){
        return articleRepository.findByAuthorUsername(name);
    }


}
