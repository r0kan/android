package com.example.application.repository;

import android.content.Context;
import com.example.application.entity.ArticleEntity;
import com.example.application.R;

import java.io.InputStream;
import java.util.*;

import android.util.Log;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ArticlesRepository {
    public Map<Long, ArticleEntity> articles = new HashMap<>();

    public ArticlesRepository(Context context) {
        try {
            InputStream inputStream = context.getResources().openRawResource(R.raw.articles);
            String jsonString = IOUtils.toString(inputStream, "utf-8");
            JSONArray articles = new JSONArray(jsonString);
            JSONObject article;
            for (long i = 0; i < articles.length(); i++) {
                article = articles.getJSONObject(((int) i));
                String title = article.getString("title");
                String author = article.getString("author");
                String content = article.getString("content");
                String tag = article.getString("tag");
                this.articles.put(i, new ArticleEntity(i, title, author, content, tag));
            }
        } catch (Exception exception) {
            Log.e("ArticlesRepository", exception.getMessage());
            for (long i = 0; i < 20; i += 1) {
                this.articles.put(i, new ArticleEntity(i, "Title " + (i + 1), "Mark I", "Content 122", null));
            }
        }
    }

    public ArticleEntity getById(Long id) {
        return this.articles.get(id);
    }

}
