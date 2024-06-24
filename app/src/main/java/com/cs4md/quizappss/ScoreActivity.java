package com.cs4md.quizappss;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTV, scoreValueTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent myIntent = getIntent();

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        scoreValueTV = (TextView) findViewById(R.id.scoreValueTV);
        int tempScore = myIntent.getIntExtra("ScoreActivity", 0);


        String msg = "" + tempScore;
        scoreValueTV.setText(msg);


    }
}