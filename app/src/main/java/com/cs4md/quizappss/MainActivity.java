package com.cs4md.quizappss;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //declare UI elements and other variables
    Button trueBTN;
    Button falseBTN;
    Button nextBTN;
    TextView questionTV, previousScoreTV;
    Toast myToast;
    int score;
    String message;
    //View mainView;
    Question q1, q2, q3, q4, q5, currentQuestion;
    Question[] questions;
    int currentIndex;
    ImageView swIMG;
    private SharedPreferences mPreferences;
    private String sharedPrefFile= "com.cs4md.android.QuizAppSS";
    private final String PREVIOUS_SCORE_KEY = "SCORE";


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
        swIMG = (ImageView) findViewById(R.id.swImg);
        previousScoreTV = (TextView) findViewById(R.id.previousScoreTV);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        //Read initial Value
        int prefScore = mPreferences.getInt(PREVIOUS_SCORE_KEY, 0);

        //Set the Previous Score
        String prevScoreString = " "+ prefScore;
        previousScoreTV.setText("Preivous Score: " + prevScoreString);

        //swIMG.setImageResource(R.drawable.yourimagename);
        //ImageView imgView=(ImageView) findViewById(R.id.imgView);
        //Drawable  drawable  = getResources().getDrawable(R.drawable.img);
       // imgView.setImageDrawable(drawable);

        // 3. what happens when user interacts with application
        trueBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                falseBTN.setEnabled(false);
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
                trueBTN.setEnabled(false);
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
                falseBTN.setEnabled(true);
                trueBTN.setEnabled(true);
                currentIndex++;
                int tempImageNum = currentQuestion.getImageNum();
                swIMG.setImageResource(tempImageNum);

                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putInt(PREVIOUS_SCORE_KEY, score);
                preferencesEditor.apply();



                //swIMG.setImageDrawable();
                //swIMG.setImageResource(R.drawable.question_one);
                /*
                switch (currentIndex){

                    case 0:
                        swIMG.setImageResource(R.drawable.question_one);
                        break;
                    case 1:
                        swIMG.setImageResource(R.drawable.question_two);
                        break;
                    case 2:
                        swIMG.setImageResource(R.drawable.question_three);
                        break;
                    case 3:
                        swIMG.setImageResource(R.drawable.question_four);
                        break;
                    case 4:
                        swIMG.setImageResource(R.drawable.question_five);
                        break;
                    default:
                        swIMG.setImageResource(R.drawable.question_five);
                        //throw new IllegalStateException("Unexpected value: " + currentIndex);
                }
                */


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
        q1 = new Question(getString(R.string.q1_text), true, R.drawable.question_one);
        q2 = new Question(getString(R.string.q2_text), true, R.drawable.question_two);
        q3 = new Question(getString(R.string.q3_text), true, R.drawable.question_three);
        q4 = new Question(getString(R.string.q4_text), false, R.drawable.question_four);
        q5 = new Question(getString(R.string.q5_text), true, R.drawable.question_five);
    }
}