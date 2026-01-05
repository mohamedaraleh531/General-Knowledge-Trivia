package com.example.mohamedsquizapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        TextView scoreText = findViewById(R.id.greeting);

        int score = getIntent().getIntExtra("score", -1);

        if (score == 100) {
            scoreText.setText("Incorrect That's a myth!");
        } else if (score == 0) {
            scoreText.setText("Correct");

        }
    }
}