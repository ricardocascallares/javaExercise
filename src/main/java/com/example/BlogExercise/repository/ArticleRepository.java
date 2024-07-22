package com.example.BlogExercise.repository;

import com.example.BlogExercise.domain.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticleRepository extends CrudRepository<Article,Long> {

      public List<Article> findByTitle(String title);

      public List<Article> findByAuthorUsername(String authorName);

      public Article findById(long id);


}
