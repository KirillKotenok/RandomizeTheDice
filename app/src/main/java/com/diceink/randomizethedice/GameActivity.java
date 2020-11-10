package com.diceink.randomizethedice;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private static final Random RANDOM = new Random();
    private TextView result_view;
    private Button roll;
    private ImageView first_dice;
    private ImageView second_dice;
    private Intent intent;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        roll = findViewById(R.id.start_roll);
        roll.setOnClickListener(this);
        result_view = findViewById(R.id.winner);
        result_view.setVisibility(TextView.GONE);
        first_dice = findViewById(R.id.dice1);
        second_dice = findViewById(R.id.dice2);
        intent = getIntent();
    }

    @Override
    public void onClick(View v) {
        int val1 = random_dice();
        int val2 = random_dice();

        timer = new CountDownTimer(2000, 70) {
            int count = 1;

            @Override
            public void onTick(long millisUntilFinished) {
                if (count < 7) {
                    int resource = getResources().getIdentifier("dice_" + count,
                            "drawable", "com.diceink.randomizethedice");
                    first_dice.setImageResource(resource);
                    second_dice.setImageResource(resource);
                    count++;
                } else {
                    count = 0;
                }
            }

            @Override
            public void onFinish() {
                int resource1 = getResources().getIdentifier("dice_" + val1,
                        "drawable", "com.diceink.randomizethedice");
                int resource2 = getResources().getIdentifier("dice_" + val2,
                        "drawable", "com.diceink.randomizethedice");

                first_dice.setImageResource(resource1);
                second_dice.setImageResource(resource2);
                if (val1 > val2) {
                    result_view.setText(String.format(getResources().getString(R.string.winner),
                            intent.getStringExtra(MainActivity.FIRST_NAME_KEY)));
                } else if (val1 == val2) {
                    result_view.setText("Draw");
                } else {
                    result_view.setText(String.format(getResources().getString(R.string.winner),
                            intent.getStringExtra(MainActivity.SECOND_NAME_KEY)));
                }
                result_view.setVisibility(TextView.VISIBLE);
            }
        }.start();
    }

    private int random_dice() {
        return RANDOM.nextInt(6) + 1;
    }
}
