package com.example.BlogExercise;

import com.example.BlogExercise.domain.Article;
import com.example.BlogExercise.domain.User;
import com.example.BlogExercise.repository.UserRepository;
import com.example.BlogExercise.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    @Transactional
    public void testPostInsertOneUser() throws Exception {

        String mockUserJson = "{\"username\": \"usernameTest\",\n \"lastname\": \"lastnameTest\"\n }";

        mockMvc.perform(MockMvcRequestBuilders.post("/users").header("apikey","12341234")
                        .content(mockUserJson)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        List<User> users = (List<User>)userRepository.findAll();

        assertEquals(1,users.size());
        assertEquals("usernameTest",users.getFirst().username);
        assertEquals("lastnameTest",users.getFirst().lastname);
    }

}
