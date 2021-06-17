package com.example.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answer = new ArrayList<>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;
    TextView introTextView;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        introTextView.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("31s");
        scoreTextView.setText(score + "/" + numberOfQuestions);

        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(31000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                resultTextView.setText("Done!");
                playAgainButton.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        } else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(score + "/" + numberOfQuestions);
        newQuestion();
    }

    public void newQuestion(){
        Random random = new Random();
        int a = random.nextInt(21);
        int b = random.nextInt(21);
        sumTextView.setText(a + " + " + b);

        locationOfCorrectAnswer = random.nextInt(4);

        answer.clear();
        for(int i = 0; i < 4; i++){
            if(i == locationOfCorrectAnswer){
                answer.add(a+b);
            } else{
                int wrongAnswer = random.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = random.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        if(answer.get(0) < 10){
            button0.setText("0" + Integer.toString(answer.get(0)));
        } else{
            button0.setText(Integer.toString(answer.get(0)));
        }
        if(answer.get(1) < 10){
            button1.setText("0" + Integer.toString(answer.get(1)));
        } else{
            button1.setText(Integer.toString(answer.get(1)));
        }
        if(answer.get(2) < 10){
            button2.setText("0" + Integer.toString(answer.get(2)));
        } else{
            button2.setText(Integer.toString(answer.get(2)));
        }
        if(answer.get(3) < 10){
            button3.setText("0" + Integer.toString(answer.get(3)));
        } else{
            button3.setText(Integer.toString(answer.get(3)));
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        goButton = findViewById(R.id.goButton);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        gameLayout = findViewById(R.id.gameLayout);
        introTextView = findViewById(R.id.introTextView);

        goButton.setVisibility(View.VISIBLE);
        introTextView.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }
}