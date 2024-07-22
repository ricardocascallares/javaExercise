package com.example.BlogExercise.domain;


import jakarta.persistence.*;


@Entity
public class Article {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    public Long id;
    public String title;
    public String content;

    @ManyToOne
    public User author;

    public Article() {}

    public Article(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public String toString(){
        return "{\"Title\": \"" + title + "\", \"Content\": \"" + content  + "\", \"Author\": " + author.toString() + "}";
    }


}
