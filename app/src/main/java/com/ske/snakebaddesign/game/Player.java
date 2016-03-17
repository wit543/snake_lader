package com.ske.snakebaddesign.game;

/**
 * Created by WIT on 16-Mar-16.
 */
public class Player implements PlayerInterface{
    private int color;
    private int position;

    public Player(int color) {
        this.color = color;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getColor() {
        return color;
    }

    public int getPosition() {
        return position;
    }
}
