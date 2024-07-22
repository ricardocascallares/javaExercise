package com.example.BlogExercise.domain.Marvel;

import java.util.List;

public class MarvelDataResponse {

    public MarvelDataResponse() {}

    public MarvelDataResponse(List<MarvelCharacter> results) {
        this.results = results;
    }

    public List<MarvelCharacter> results;

    public List<MarvelCharacter> getResults() {
        return results;
    }

    public void setResults(List<MarvelCharacter> results) {
        this.results = results;
    }

}
