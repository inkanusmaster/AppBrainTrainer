package com.example.setion5_appbraintrainer;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.TreeSet;

public class MainActivity extends AppCompatActivity {

    boolean firstGame = true;
    long time;
    double result;
    int additionResult, goodAnswers, totalAnswers;
    GridLayout answerGridLayout;
    Button startButton, playAgainButton, restartButton, button1, button2, button3, button4;
    TextView timerTextView, mathTextView, pointsTextView, answerTextView, rulesTextView;
    CountDownTimer timer;

    public void createGlobalVars() {
        timerTextView = findViewById(R.id.timerTextView);
        mathTextView = findViewById(R.id.mathTextView);
        pointsTextView = findViewById(R.id.pointsTextView);
        answerGridLayout = findViewById(R.id.answersGridLayout);
        startButton = findViewById(R.id.startButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        restartButton = findViewById(R.id.restartButton);
        answerTextView = findViewById(R.id.answerTextView);
        rulesTextView = findViewById(R.id.rulesTextView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }

    @SuppressLint("SetTextI18n")
    public void declareStartGameVars() {
        goodAnswers = 0;
        totalAnswers = 0;
        result = 0;
        time = 30;
        timerTextView.setVisibility(View.VISIBLE);
        mathTextView.setVisibility(View.VISIBLE);
        pointsTextView.setVisibility(View.VISIBLE);
        pointsTextView.setText(goodAnswers + "/" + totalAnswers);
        answerGridLayout.setVisibility(View.VISIBLE);
        startButton.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        restartButton.setVisibility(View.VISIBLE);
        answerTextView.setVisibility(View.INVISIBLE);
        rulesTextView.setVisibility(View.INVISIBLE);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        button4.setClickable(true);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void declareEndGameVars() {
        if (totalAnswers == 0) {
            answerTextView.setText("Game finished!\nResult: 0%");
        } else {
            result = (float) goodAnswers / totalAnswers;
            answerTextView.setText("Game finished!\nResult: " + String.format("%,.2f", result * 100) + "%");
        }
        restartButton.setVisibility(View.INVISIBLE);
        answerTextView.setVisibility(View.VISIBLE);
        playAgainButton.setVisibility(View.VISIBLE);
        button1.setClickable(false);
        button2.setClickable(false);
        button3.setClickable(false);
        button4.setClickable(false);
    }

    public void countDownTimer() {
        timer = new CountDownTimer(time * 1000, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long milisecondsTillEnd) {
                time = milisecondsTillEnd / 1000;
                timerTextView.setText(time + "s");
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {
                declareEndGameVars();
            }
        };
        timer.start();
    }

    @SuppressLint("SetTextI18n")
    public void randomNumbers() {
        int x = (int) ((Math.random() * (30)) + 1);
        int y = (int) ((Math.random() * (30)) + 1);
        mathTextView.setText(x + " + " + y);
        additionResult = x + y;
        TreeSet<Integer> answers = new TreeSet<>();
        answers.clear();
        answers.add(additionResult);
        while (answers.size() < 4) {
            answers.add((int) ((Math.random() * (60)) + 1));
        }

        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
            Button button = (Button) answerGridLayout.getChildAt(i);
            button.setText(String.valueOf(answers.pollFirst()));
            if (button.getText() == String.valueOf(additionResult)) {
                button.setTag("good");
            } else button.setTag("bad");
        }
    }

    @SuppressLint("SetTextI18n")
    public void checkAnswer(View view) {
        if (view.getTag().equals("good")) {
            goodAnswers++;
            answerTextView.setText("Good answer :)");
        } else {
            answerTextView.setText("Bad answer :(");
        }
        answerTextView.setVisibility(View.VISIBLE);
        totalAnswers++;
        pointsTextView.setText(goodAnswers + "/" + totalAnswers);
        randomNumbers();
    }

    public void closeApp(View view) {
        finish();
    }

    public void startGame(View view) {
        if(!firstGame){
            timer.cancel();
        }
        firstGame = false;
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
