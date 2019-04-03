package com.example.application.entity;

public class ArticleEntity {
    public final Long id;
    public final String title;
    public final String author;
    public final String tag;
    public final String content;

    public ArticleEntity(long id, String title, String author, String content, String tag) {
        this.id = id;
        this.title = title;
        this.author = author;
        if (tag != null) {
            this.tag = tag;
        } else {
            this.tag = "Android";
        }
        this.content = content;
    }
}
