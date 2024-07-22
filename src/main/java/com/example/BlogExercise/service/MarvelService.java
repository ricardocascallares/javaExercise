package com.example.BlogExercise.service;

import com.example.BlogExercise.domain.Marvel.MarvelCharacter;
import com.example.BlogExercise.domain.Marvel.MarvelResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class MarvelService {

    RestTemplate restTemplate = new RestTemplate();

    public List<MarvelCharacter> marvelCharacters(){
        String url="https://gateway.marvel.com/v1/public/characters?ts=1&apikey=c8ff90e34172f215554a23db26233ea2&hash=910be87041aec0a1fd438b60095f02c4";
        return restTemplate.getForObject(url,MarvelResponse.class).getResults();
    }

    public List<MarvelCharacter> marvelCharacterById(String characterId){
        String url="https://gateway.marvel.com:443/v1/public/characters/%s?ts=1&apikey=c8ff90e34172f215554a23db26233ea2&hash=910be87041aec0a1fd438b60095f02c4".formatted(characterId);
        return restTemplate.getForObject(url, MarvelResponse.class).getResults();
    }
}
