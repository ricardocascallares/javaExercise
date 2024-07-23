package com.example.BlogExercise;

import com.example.BlogExercise.domain.Marvel.MarvelCharacter;
import com.example.BlogExercise.domain.Marvel.MarvelDataResponse;
import com.example.BlogExercise.domain.Marvel.MarvelResponse;
import com.example.BlogExercise.service.MarvelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
public class MarvelTest {




    @Autowired
    private MockMvc mockMvc;

    @Mock
    private RestTemplate restTemplate;


    @InjectMocks
    private MarvelService marvelService;




    @Test
    public void getAllMarvelCharactersAndReturnTheListWithRick() throws Exception {

        MarvelCharacter marvelCharacter = new MarvelCharacter();
        marvelCharacter.setName("Rick");
        List<MarvelCharacter> marvelCharacters = new ArrayList<>();
        marvelCharacters.add(marvelCharacter);

        MarvelResponse marvelResponse = new MarvelResponse(new MarvelDataResponse(marvelCharacters));
        String url="https://gateway.marvel.com/v1/public/characters?ts=1&apikey=c8ff90e34172f215554a23db26233ea2&hash=910be87041aec0a1fd438b60095f02c4";
        Mockito.when(restTemplate.getForObject(url, MarvelResponse.class)).thenReturn(marvelResponse);

        List<MarvelCharacter> testMarvelCharactersResponse = marvelService.marvelCharacters();

        assertEquals(marvelResponse.getResults(), testMarvelCharactersResponse);

    }

    @Test
    public void getIronMAnMarvelCharacterAndReceiveTheInformation() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/integrations/marvel/characters/1009368").header("apikey","12341234"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Iron Man"));
    }
}
