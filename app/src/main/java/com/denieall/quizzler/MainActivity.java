package com.denieall.quizzler;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnTrue;
    Button btnFalse;
    ProgressBar mProgressBar;
    TextView question_textview, score_textview;


    // Quiz Answers
    // True
    // False
    // True
    // False
    // True
    // True
    // True
    // True
    // False
    // True
    // True
    // True
    // False

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        mProgressBar = findViewById(R.id.question_progressBar);
        question_textview = findViewById(R.id.question_textView);
        score_textview = findViewById(R.id.score_textView);

        // Event listener for true button
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "True", Toast.LENGTH_LONG).show();
            }
        };

        btnTrue.setOnClickListener(onClickListener);

        // Event listener for false button
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "False", Toast.LENGTH_LONG).show();
            }
        });
    }


}
