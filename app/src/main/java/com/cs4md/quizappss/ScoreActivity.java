package com.cs4md.quizappss;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class ScoreActivity extends AppCompatActivity {

    TextView scoreTV, scoreValueTV;
    Button emailBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent myIntent = getIntent();

        scoreTV = (TextView) findViewById(R.id.scoreTV);
        scoreValueTV = (TextView) findViewById(R.id.scoreValueTV);
        emailBTN = (Button) findViewById(R.id.emailBTN);
        int tempScore = myIntent.getIntExtra("ScoreActivity", 0);

        String msg = "Score: " + tempScore;
        scoreValueTV.setText(msg);

        emailBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String [] addresses = new String[] {"spsell@gmail.com"};
                String subject = getString(R.string.look_at_my_score_text);
                String body = getString(R.string.email_body_fragment_1) + tempScore + getString(R.string.email_body_fragment_2);

                //send email
                composeEmail(addresses, subject, body);
            }
        });
    }


   // @SuppressLint("IntentReset")
    private void composeEmail(String[] addresses, String subject, String body) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:")); // Only email apps handle this.
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


}