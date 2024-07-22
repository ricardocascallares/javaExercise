package com.example.BlogExercise.controller;

import com.example.BlogExercise.domain.Marvel.MarvelCharacter;
import com.example.BlogExercise.service.MarvelService;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class IntegrationController {

    private Logger logger = Logger.getLogger(IntegrationController.class);

    @Autowired
    private MarvelService marvelService;

    @GetMapping("/integrations/marvel/characters")
    public List<MarvelCharacter> marvelCharacters() {
        logger.info("Getting marvel characters");
        List<MarvelCharacter> responseMarvel = marvelService.marvelCharacters();
        logger.info(responseMarvel.toString());
        return responseMarvel;
    }

    @GetMapping("/integrations/marvel/characters/{characterId}")
    public List<MarvelCharacter> marvelCharacter(@PathVariable String characterId) {
        logger.info("Getting marvel character with id " + characterId);
        List<MarvelCharacter> responseMarvel = marvelService.marvelCharacterById(characterId);
        logger.info(responseMarvel.toString());
        return  responseMarvel;
    }
}
