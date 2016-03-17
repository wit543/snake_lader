package com.ske.snakebaddesign.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ske.snakebaddesign.R;
import com.ske.snakebaddesign.game.Game;
import com.ske.snakebaddesign.guis.BoardView;

public class GameActivity extends AppCompatActivity {
    private Game game;

    private BoardView boardView;
    private TextView textPlayerTurn;
    private Button buttonTakeTurn;
    private Button buttonRestart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        game.resetGame();
    }

    private void initComponents() {


        boardView = (BoardView) findViewById(R.id.board_view);
        buttonTakeTurn = (Button) findViewById(R.id.button_take_turn);
        buttonTakeTurn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.takeTurn();
            }
        });
        buttonRestart = (Button) findViewById(R.id.button_restart);
        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.resetGame();
            }
        });
        textPlayerTurn = (TextView) findViewById(R.id.text_player_turn);

        game = new Game(this);
        game.setBoardView(boardView);
        game.setTextPlayerTurn(textPlayerTurn);
    }



}
