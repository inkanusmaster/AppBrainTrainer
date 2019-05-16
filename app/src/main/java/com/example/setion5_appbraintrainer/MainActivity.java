package com.example.setion5_appbraintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    long time;
    int additionResult;
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

    @SuppressLint("SetTextI18n")
    public void randomNumbers(){
        int x = (int)((Math.random()*(30)) + 1);
        int y = (int)((Math.random()*(30)) + 1);
        mathTextView.setText(x+" + "+y);
        additionResult = x+y;
        Log.i("Result",String.valueOf(additionResult));

        TreeSet<Integer> answers = new TreeSet<>();
        answers.clear();
        answers.add(additionResult);
        while (answers.size()<4) {
            answers.add((int)((Math.random()*(60)) + 1));
        }
        Log.i("Answer list",String.valueOf(answers));

        for (int i=0; i< answerGridLayout.getChildCount(); i++){
            Button button = (Button) answerGridLayout.getChildAt(i);
            button.setText(String.valueOf(answers.pollFirst()));
        }
    }

    public void startGame(View view){
        declareStartGameVars();
        countDownTimer();
        randomNumbers();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGlobalVars();
    }
}
