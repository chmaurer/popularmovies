package com.chmaurer.android.popularmovies.data;

/**
 Created by Christian on 06.01.2016.
 */
public class Review {
    private String id;
    private String author;
    private String content;

    public String getId () {
        return id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getAuthor () {
        return author;
    }

    public void setAuthor (String author) {
        this.author = author;
    }

    public String getContent () {
        return content;
    }

    public void setContent (String content) {
        this.content = content;
    }
}

