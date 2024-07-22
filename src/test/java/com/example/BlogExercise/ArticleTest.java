package com.example.BlogExercise;

import com.example.BlogExercise.domain.Article;
import com.example.BlogExercise.domain.User;
import com.example.BlogExercise.repository.ArticleRepository;
import com.example.BlogExercise.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureMockMvc
@SpringBootTest
public class ArticleTest {



    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;


    @AfterEach
    public void tearDown() {
        articleRepository.deleteAll();
        userRepository.deleteAll();
    }


    @Test
    public void testGetAllArticlesCountWithOneInsert() throws Exception {

        User author = userRepository.save(new User("username Test","lastname Test"));

        articleRepository.save(new Article("Title Test","Conten test",author));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles").header("api-key","apiKeyTest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }
    @Test
    public void testGetAllArticlesCountWithTwoInsert() throws Exception {

        User author = userRepository.save(new User("username Test","lastname Test"));

        articleRepository.save(new Article("Title Test","Conten test",author));
        articleRepository.save(new Article("Title Test","Conten test",author));

        mockMvc.perform(MockMvcRequestBuilders.get("/articles").header("api-key","apiKeyTest"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }


    @Test
    public void testGetAllArticlesCountWithoutApiKeyAndReceiveBadRequestException() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/articles"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void testGetAllArticlesCountWithBadApiKeyAndReceiveBadRequestException() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/articles").header("api-key","TestApiKey"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());



    }

    @Test
    public void testFindById() throws Exception {

        User author = userRepository.save(new User("username Test","lastname Test"));
        Article article= new Article("test looking for id","Content test",author);

        articleRepository.save(article);

        mockMvc.perform(MockMvcRequestBuilders.get("/articles/find/{id}",article.id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("test looking for id"));
    }

    @Test
    public void testFindByAuthor() throws Exception {


        User authorRicardo = userRepository.save(new User("Ricardo","lastname Test"));
        User authorRick = userRepository.save(new User("Rick","lastname Test"));

        articleRepository.save(new Article("Title Test","Content test",authorRicardo));
        articleRepository.save(new Article("Title Test","Content test",authorRick));


        mockMvc.perform(MockMvcRequestBuilders.get("/articles/findByAuthor/{author}",authorRicardo.username))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].author.username").value("Ricardo"));
    }


}


