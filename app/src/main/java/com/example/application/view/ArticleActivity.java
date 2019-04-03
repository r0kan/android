package com.example.application.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import android.view.*;
import com.example.application.R;
import com.example.application.repository.ArticlesRepository;
import com.example.application.entity.ArticleEntity;

public class ArticleActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Intent intent = getIntent();
        Long id = intent.getLongExtra(MainActivity.EXTRA_MESSAGE, 0);
        Log.v("ArticleActivity", "Intent id:" + id);

        ArticlesRepository articlesRepository = new ArticlesRepository(getApplicationContext());
        ArticleEntity article = articlesRepository.getById(id);

        if (article != null) {
            TextView titleView = findViewById(R.id.article_title);
            titleView.setText(article.title);

            TextView authorView = findViewById(R.id.article_author);
            authorView.setText(article.author);
            authorView.setOnClickListener(this);

            TextView contentView = findViewById(R.id.article_content);
            contentView.setText(article.content);

            TextView tagView = findViewById(R.id.article_tag);
            tagView.setText(article.tag);
        }


        Button backButton = findViewById(R.id.article_back_button);
        backButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.article_back_button: {
                Log.v("ArticleActivity", "back to previous activity");
                this.finish();
                break;
            }

            case R.id.article_author: {
                Intent intent = new Intent(this, AuthorActivity.class);
                Log.v("AuthorActivity", "start AuthorActivity ");
                startActivity(intent);
                break;
            }
        }

    }
}
