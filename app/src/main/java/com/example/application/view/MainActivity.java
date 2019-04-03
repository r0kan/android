package com.example.application.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.util.Log;

import com.example.application.R;
import com.example.application.entity.ArticleEntity;
import com.example.application.repository.ArticlesRepository;

public class MainActivity extends AppCompatActivity implements ArticlesRecyclerViewAdapter.ClickListener {
    public static final String EXTRA_MESSAGE = "com.example.application.MESSAGE";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // articles
        ArticlesRepository articlesRepository = new ArticlesRepository(getApplicationContext());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ArticlesRecyclerViewAdapter adapter = new ArticlesRecyclerViewAdapter(articlesRepository.articles);
        adapter.setOnItemClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.articles);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    public void onItemClick(ArticleEntity articleEntity, View view) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra(EXTRA_MESSAGE, articleEntity.id);
        Log.v("MainActivity", "start ArticleActivity for id:" + articleEntity.id);
        startActivity(intent);
    }

    public void onItemLongClick(ArticleEntity articleEntity, View view) {
        Log.d("MainAcitivity", "on item long click position:" + articleEntity.id);
    }
}
