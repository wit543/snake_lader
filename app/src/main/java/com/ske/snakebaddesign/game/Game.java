package com.ske.snakebaddesign.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.widget.TextView;

import com.ske.snakebaddesign.guis.BoardView;

import java.util.Random;

/**
 * Created by WIT on 17-Mar-16.
 */
public class Game {
    private int boardSize;
    private PlayerInterface p1;
    private PlayerInterface p2;
    private Board board;
    private int turn;
    private BoardView boardView;
    private TextView textPlayerTurn;
    private Activity activity;

    public Game(Activity activity) {
        this.activity = activity;
    }

    public void setBoardView(BoardView boardView) {
        this.boardView = boardView;
    }

    public void setTextPlayerTurn(TextView textPlayerTurn) {
        this.textPlayerTurn = textPlayerTurn;
    }

    public void resetGame() {
        turn = 0;
        boardSize = 6;
        p1 = new Player(Color.WHITE);
        p2 = new Player(Color.BLACK);
        board = new Board(p1,p2,boardSize);
        boardView.setBoard(board);
    }
    public void takeTurn() {
        final int value = 1 + new Random().nextInt(6);
        String title = "You rolled a die";
        String msg = "You got " + value;
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                moveCurrentPiece(value);
                dialog.dismiss();
            }
        };
        displayDialog(title, msg, listener);
    }

    private void moveCurrentPiece(int value) {
        if (turn % 2 == 0) {
            p1.setPosition(adjustPosition(p1.getPosition(), value));
            textPlayerTurn.setText("Player 2's Turn");
        } else {
            p2.setPosition(adjustPosition(p2.getPosition(), value));
            textPlayerTurn.setText("Player 1's Turn");
        }
        boardView.render();
        checkWin();
        turn++;
    }

    private int adjustPosition(int current, int distance) {
        current = current + distance;
        int maxSquare = boardSize * boardSize - 1;
        if(current > maxSquare) {
            current = maxSquare - (current - maxSquare);
        }
        return current;
    }

    private void checkWin() {
        String title = "Game Over";
        String msg = "";
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                resetGame();
                dialog.dismiss();
            }
        };
        if (p1.getPosition() == boardSize * boardSize - 1) {
            msg = "Player 1 won!";
        } else if (p2.getPosition() == boardSize * boardSize - 1) {
            msg = "Player 2 won!";
        } else {
            return;
        }
        displayDialog(title, msg, listener);
    }

    private void displayDialog(String title, String message, DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", listener);
        alertDialog.show();
    }
}
