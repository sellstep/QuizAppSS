package com.cs4md.quizappss;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
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
        score = 0;
        message = "";
        final MediaPlayer clickSound = MediaPlayer.create(this, R.raw.question_sound1);
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
                /*
                next 2 lines of code are for the image
                 */
                int tempImageNum = currentQuestion.getImageNum();
                swIMG.setImageResource(tempImageNum);
                /*
                next 3 lines of code are for the sound
                 */
                int tempSound = currentQuestion.getQuestionSound();
                MediaPlayer questionsSound = MediaPlayer.create(MainActivity.this, tempSound);
                Log.d("tempSound ID", "tempSound" + tempSound);

                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putInt(PREVIOUS_SCORE_KEY, score);
                preferencesEditor.apply();

                if( currentIndex < questions.length){
                    //advance and show next question
                    currentQuestion = questions[currentIndex];
                    questionTV.setText(currentQuestion.getqText());
                    falseBTN.setBackgroundColor(Color.parseColor("#701466"));
                    trueBTN.setBackgroundColor(Color.parseColor("#701466"));
                    questionsSound.start();
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
        q1 = new Question(getString(R.string.q1_text), true, R.drawable.question_one, R.raw.question_sound2);
        q2 = new Question(getString(R.string.q2_text), true, R.drawable.question_two, R.raw.question_sound3);
        q3 = new Question(getString(R.string.q3_text), true, R.drawable.question_three, R.raw.question_sound4);
        q4 = new Question(getString(R.string.q4_text), false, R.drawable.question_four, R.raw.question_sound1);
        q5 = new Question(getString(R.string.q5_text), true, R.drawable.question_five, R.raw.question_sound6);
    }
}