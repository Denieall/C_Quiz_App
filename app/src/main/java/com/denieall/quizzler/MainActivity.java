package com.denieall.quizzler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    Button btnTrue;
    Button btnFalse;
    Button btn_retry;
    ProgressBar mProgressBar;
    TextView question_textview, score_textview, result_textview;
    LinearLayout btn_layout;
    RelativeLayout main_layout;
    ImageView result_image;
    int counter = 0;
    int score = 0;
    int progress = 0;
    boolean quiz_end = false;


    // Setting array of objects
    private QuestionAnswer[] qa = new QuestionAnswer[] {
            new QuestionAnswer(R.string.question_1, true),
            new QuestionAnswer(R.string.question_2, false),
            new QuestionAnswer(R.string.question_3, true),
            new QuestionAnswer(R.string.question_4, false),
            new QuestionAnswer(R.string.question_5, true),
            new QuestionAnswer(R.string.question_6, true),
            new QuestionAnswer(R.string.question_7, true),
            new QuestionAnswer(R.string.question_8, true),
            new QuestionAnswer(R.string.question_9, false),
            new QuestionAnswer(R.string.question_10, true),
            new QuestionAnswer(R.string.question_11, true),
            new QuestionAnswer(R.string.question_12, true),
            new QuestionAnswer(R.string.question_13, false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTrue = findViewById(R.id.btn_true);
        btnFalse = findViewById(R.id.btn_false);
        mProgressBar = findViewById(R.id.question_progressBar);
        question_textview = findViewById(R.id.question_textView);
        score_textview = findViewById(R.id.score_textView);
        result_image = findViewById(R.id.result_imgview);
        result_textview = findViewById(R.id.result_textView);
        btn_layout = findViewById(R.id.btn_linearLayout);
        main_layout = findViewById(R.id.main_layout);
        btn_retry = findViewById(R.id.btn_retry);

        mProgressBar.setVisibility(View.VISIBLE);
        question_textview.setVisibility(View.VISIBLE);
        score_textview.setVisibility(View.VISIBLE);
        btn_layout.setVisibility(View.VISIBLE);
        result_textview.setVisibility(View.GONE);
        result_image.setVisibility(View.GONE);
        btn_retry.setVisibility(View.GONE);

        // Event listener for true button
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkanswer(true);
                updateQuestion();
            }
        });

        // Event listener for false button
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkanswer(false);
                updateQuestion();
            }
        });

        // Event listener for button retry
        btn_retry.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                counter = 0;
                score = 0;
                progress = 0;
                quiz_end = false;

                mProgressBar.setVisibility(View.VISIBLE);
                question_textview.setVisibility(View.VISIBLE);
                score_textview.setVisibility(View.VISIBLE);
                btn_layout.setVisibility(View.VISIBLE);
                result_textview.setVisibility(View.GONE);
                result_image.setVisibility(View.GONE);
                btn_retry.setVisibility(View.GONE);

                question_textview.setText(qa[counter].getQuestionID());
                mProgressBar.setProgress(progress);
                score_textview.setText("Score:" + score + "/" + (qa.length));
            }
        });

        // If savedInstanceState is not null
        if (savedInstanceState != null) {
            counter = savedInstanceState.getInt("IndexKey");
            score = savedInstanceState.getInt("ScoreKey");
            progress = savedInstanceState.getInt("ProgressKey");
            quiz_end = savedInstanceState.getBoolean("QuizEndKey");

            if (quiz_end) {
                mProgressBar.setVisibility(View.GONE);
                question_textview.setVisibility(View.GONE);
                score_textview.setVisibility(View.GONE);
                btn_layout.setVisibility(View.GONE);

                if (score > 10) {
                    result_image.setImageResource(R.drawable.trophy);
                } else {
                    result_image.setImageResource(R.drawable.sad);
                }
                result_textview.setText("Score:" + score + "/" + (qa.length));
                result_textview.setVisibility(View.VISIBLE);
                result_image.setVisibility(View.VISIBLE);
                btn_retry.setVisibility(View.VISIBLE);
            } else {
                question_textview.setText(qa[counter].getQuestionID());
                mProgressBar.setProgress(progress);
                score_textview.setText("Score:" + score + "/" + (qa.length));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("ScoreKey", score);
        outState.putInt("IndexKey", counter);
        outState.putInt("ProgressKey", progress);
        outState.putBoolean("QuizEndKey", quiz_end);
    }

    // Check the answer and update score
    private void checkanswer(Boolean userAnswer) {
        if (counter < qa.length) {

            if (qa[counter].getAnswer() == userAnswer) {
                Toast.makeText(getApplicationContext(),  R.string.correct_toast, Toast.LENGTH_SHORT).show();
                score++;
                score_textview.setText("Score:" + score + "/" + (qa.length));
            } else {
                Toast.makeText(getApplicationContext(),  R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
            }

        }
    }

    // Change new question on button click and update progress bar
    private void updateQuestion() {

        counter++;

        if (counter < qa.length) {

            question_textview.setText(qa[counter].getQuestionID());

            progress = Math.round((100 * (counter+1))/13);
            mProgressBar.setProgress(progress);

        } else {

            quiz_end = true;
            mProgressBar.setVisibility(View.GONE);
            question_textview.setVisibility(View.GONE);
            score_textview.setVisibility(View.GONE);
            btn_layout.setVisibility(View.GONE);

            if (score > 10) {
                result_image.setImageResource(R.drawable.trophy);
            } else {
                result_image.setImageResource(R.drawable.sad);
            }
            result_textview.setVisibility(View.VISIBLE);
            result_image.setVisibility(View.VISIBLE);
            btn_retry.setVisibility(View.VISIBLE);



//            // Set alert example
//            // set cancelable means click anywhere on the screen to close the pop up (false prevents this)
//            AlertDialog.Builder alert = new AlertDialog.Builder(this).setTitle("Finished!").setCancelable(false)
//                    .setMessage("Final score: " + score + "/" + (qa.length));
//
//            alert.setPositiveButton("Close", new DialogInterface.OnClickListener() {
//
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    // Closing the app
//                    finish();
//                }
//            });
//
//            alert.setNegativeButton("Try Again", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    counter = 0;
//                    score = 0;
//                    score_textview.setText("");
//                    question_textview.setText(qa[0].getQuestionID());
//                    mProgressBar.setProgress(0);
//                }
//            });
//
//            alert.show();


        }

    }


}
