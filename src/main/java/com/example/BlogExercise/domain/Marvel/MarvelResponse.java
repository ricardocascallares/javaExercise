package com.example.BlogExercise.domain.Marvel;

import java.util.List;

public class MarvelResponse {
    private MarvelDataResponse data;

    public MarvelResponse() { }

    public MarvelResponse(MarvelDataResponse data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(MarvelDataResponse data) {
        this.data = data;
    }

    public List<MarvelCharacter> getResults(){
        return data.getResults();
    }


}