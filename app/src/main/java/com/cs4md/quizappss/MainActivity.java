package com.cs4md.quizappss;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
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
    Button nextBTN;
    TextView questionTV;
    Toast myToast;
    int score;
    String message;
    //View mainView;
    Question q1, q2, q3, q4, q5, currentQuestion;
    Question[] questions;
    int currentIndex;
    Image swIMG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 2. initialization of variables & inflating UI elements
        trueBTN = (Button) findViewById(R.id.trueBTN);
        falseBTN = (Button) findViewById(R.id.falseBTN);
        nextBTN = (Button) findViewById(R.id.nextBTN);
        questionTV = (TextView) findViewById(R.id.questionTV);
        //mainView = findViewById(R.id.rootView);
        score = 0;
        message = "";
        setQuestions();
        questions = new Question[] {q1, q2, q3, q4, q5};
        currentIndex = 0;
        currentQuestion = questions[currentIndex];
        questionTV.setText(currentQuestion.getqText());
        //swIMG = (android.widget.ImageView) findViewById(R.id.swImg);

        // 3. what happens when user interacts with application
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentQuestion.isCorrectAnswer()){
                    score++;
                    message = getString(R.string.correct_text);
                    trueBTN.setBackgroundColor(Color.parseColor("#00ff00"));
                }
                else{
                    message = getString(R.string.incorrect_text);
                    trueBTN.setBackgroundColor(Color.parseColor("#ff0000"));
                }

                myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();

            }
        });

        falseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!currentQuestion.isCorrectAnswer()){
                    score++;
                    message = getString(R.string.correct_text);
                    falseBTN.setBackgroundColor(Color.parseColor("#00ff00"));
                }
                else{
                    message = getString(R.string.incorrect_text);
                    falseBTN.setBackgroundColor(Color.parseColor("#ff0000"));
                }

                myToast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
                myToast.show();
            }
        });

        nextBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                currentIndex++;
                if( currentIndex < questions.length){
                    //advance and show next question
                    currentQuestion = questions[currentIndex];
                    questionTV.setText(currentQuestion.getqText());
                    falseBTN.setBackgroundColor(Color.parseColor("#701466"));
                    trueBTN.setBackgroundColor(Color.parseColor("#701466"));
                }
                else{
                    // declare and initialize intent
                    Intent myIntent = new Intent(MainActivity.this, ScoreActivity.class);
                    // put extra information if needed
                    myIntent.putExtra("ScoreActivity", score);

                    // start new Activity
                    startActivity(myIntent);
                }

            }
        });


    }

    private void setQuestions(){
        q1 = new Question(getString(R.string.q1_text), true);
        q2 = new Question(getString(R.string.q2_text), true);
        q3 = new Question(getString(R.string.q3_text), true);
        q4 = new Question(getString(R.string.q4_text), false);
        q5 = new Question(getString(R.string.q5_text), true);
    }
}