package com.example.application.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.application.R;

public class AuthorActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        Button backButton = findViewById(R.id.author_back_button);
        backButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        Log.v("AuthorActivity", "back to previous activity");
        this.finish();
    }
}
