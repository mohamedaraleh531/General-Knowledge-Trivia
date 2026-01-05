package com.example.mohamedsquizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView myTextView;
    Button trueButton, falseButton, emailButton;
    ImageView questionImage;

    // Questions
    String[] questions = {
            "The Great Wall of China can be seen from space with the naked eye.",
            "A group of owls is called a parliament.",
            "Diamonds are made entirely of compressed carbon.",
            "Are Dolphins Fish?",
            "Humans share about 60% of their DNA with bananas."
    };

    boolean[] answers = {false, true, true, false, true};

    String[] explanations = {
            "False — That’s a myth! The Great Wall is not visible from space with the naked eye.",
            "True — A group of owls is called a parliament.",
            "True — Diamonds are pure carbon crystals.",
            "False — Dolphins are mammals, not fish.",
            "True — Humans share about 60% of their DNA with bananas."
    };

    int[] images = {
            R.drawable.greatwallofchina,
            R.drawable.parliment,
            R.drawable.diamonds,
            R.drawable.dolphin,
            R.drawable.dna
    };

    int currentQuestion = 0;
    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myTextView = findViewById(R.id.greeting);
        trueButton = findViewById(R.id.submit);
        falseButton = findViewById(R.id.submit2);
        questionImage = findViewById(R.id.imageView3);
        emailButton = findViewById(R.id.emailButton);

        emailButton.setVisibility(View.GONE); // hide button initially

        myTextView.setText(questions[currentQuestion]);
        questionImage.setImageResource(images[currentQuestion]);

        trueButton.setOnClickListener(v -> checkAnswer(true));
        falseButton.setOnClickListener(v -> checkAnswer(false));

        emailButton.setOnClickListener(v -> sendScoreByEmail());
    }

    private void checkAnswer(boolean userAnswer) {
        if (answers[currentQuestion] == userAnswer) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }

        currentQuestion++;

        if (currentQuestion < questions.length) {
            myTextView.setText(questions[currentQuestion]);
            questionImage.setImageResource(images[currentQuestion]);
        } else {
            trueButton.setVisibility(View.GONE);
            falseButton.setVisibility(View.GONE);
            questionImage.setVisibility(View.GONE);
            emailButton.setVisibility(View.VISIBLE); // show email button

            String result = "You scored " + score + "/" + questions.length + "\n\n";
            for (int i = 0; i < questions.length; i++) {
                result += (i + 1) + ". " + questions[i] + " - " + (answers[i] ? "True" : "False") + "\n";
                result += "   Explanation: " + explanations[i] + "\n\n";
            }
            myTextView.setText(result);
        }
    }

    private void sendScoreByEmail() {
        String message = "I scored " + score + "/" + questions.length + " on the quiz.\n\n";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT, "My Quiz Score");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(Intent.createChooser(intent, "Send email with:"));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email apps installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
