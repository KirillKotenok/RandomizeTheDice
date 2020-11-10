package com.diceink.randomizethedice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String FIRST_NAME_KEY = "FIRST_NAME";
    public static final String SECOND_NAME_KEY = "SECOND_NAME";
    private static final String SAVE_FIRST_PLAYER = "player_button1";
    private static final String SAVE_SECOND_PLAYER = "player_button2";
    private static final String START_GAME = "start";
    private TextInputLayout first_player;
    private TextInputLayout second_player;
    private Button button_player1;
    private Button button_player2;
    private Button start_button;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first_player = findViewById(R.id.first_name_input);
        second_player = findViewById(R.id.second_name_input);
        button_player1 = findViewById(R.id.player_button1);
        button_player1.setOnClickListener(this);
        button_player2 = findViewById(R.id.player_button2);
        button_player2.setOnClickListener(this);
        start_button = findViewById(R.id.start);
        start_button.setOnClickListener(this);
        intent = new Intent(MainActivity.this, GameActivity.class);
    }


    @Override
    public void onClick(View v) {
        Button recived = findViewById(v.getId());
        switch (recived.getResources().getResourceEntryName(recived.getId())) {
            case SAVE_FIRST_PLAYER:
                if (first_player.getEditText().getText().toString()!=null &&
                        !first_player.getEditText().getText().toString().isEmpty()) {
                    intent.putExtra(FIRST_NAME_KEY, first_player.getEditText().getText().toString());
                }
                break;
            case SAVE_SECOND_PLAYER:
                if (second_player.getEditText().getText().toString()!=null &&
                        !second_player.getEditText().getText().toString().isEmpty()) {
                    intent.putExtra(SECOND_NAME_KEY, second_player.getEditText().getText().toString());
                }
                break;
            case START_GAME:
                if (intent.getExtras().get(FIRST_NAME_KEY)!=null && intent.getExtras().get(SECOND_NAME_KEY)!=null){
                    startActivity(intent);
                }else {
                    first_player.setPlaceholderText("please enter the name");
                    second_player.setPlaceholderText("please enter the name");
                }
                break;
        }
    }
}