package com.example.setion5_appbraintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    long time;
    GridLayout answerGridLayout;
    Button startButton, playAgainButton, button1, button2, button3, button4;
    TextView timerTextView, mathTextView, pointsTextView, answerTextView;

    public void createGlobalVars(){
        timerTextView = findViewById(R.id.timerTextView);
        mathTextView = findViewById(R.id.mathTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        answerGridLayout = findViewById(R.id.answersGridLayout);
        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        answerTextView = findViewById(R.id.answerTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }

    public void declareStartGameVars(){
        time = 30;
        timerTextView.setVisibility(View.VISIBLE);
        mathTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        answerGridLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        answerTextView.setVisibility(View.INVISIBLE);
    }

    @SuppressLint("SetTextI18n")
    public void declareEndGameVars(){
        answerTextView.setText("Game finished!");
        answerTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
        button4.setClickable(false);
    }

    public void countDownTimer(){
        new CountDownTimer(time * 1000, 1000){
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long milisecondsTillEnd) {
                time = milisecondsTillEnd / 1000;
                timerTextView.setText(time +"s");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                declareEndGameVars();
            }
        }.start();
    }

    public void randomQuestionNumbers(){

    }

    public void startGame(View view){
        declareStartGameVars();
        countDownTimer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGlobalVars();
    }
}
