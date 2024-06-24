package com.cs4md.quizappss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    //declare UI elements and other variables
    Button trueBTN;
    Button falseBTN;
    Button doneBTN;
    TextView questionTV;
    Toast myToast;
    int score;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. initialization of variables & inflating UI elements
        trueBTN = (Button) findViewById(R.id.trueBTN);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        doneBTN = (Button) findViewById(R.id.doneBTN);
        questionTV = (TextView) findViewById(R.id.questionTV);
        score = 0;
        message = "";

        // 3. what happens when user interacts with application
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score++;
                message = "Correct!";
                myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = "Incorrect.";
                myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        doneBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // declare and initialize intent
                Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                // put extra information if needed
                myIntent.putExtra("ScoreActivity", score);

                // start new Activity
                startActivity(myIntent);
            }
        });


    }
}