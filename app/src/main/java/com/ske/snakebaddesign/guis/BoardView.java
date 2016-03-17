package com.ske.snakebaddesign.guis;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import com.ske.snakebaddesign.game.Board;

public class BoardView extends View {

    private Board board;
    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        board.reloadViewDimensions(getWidth());
        board.drawBoard(canvas);
        board.drawSquares(canvas,getContext());
        board.drawPlayerPieces(canvas);
    }

    public void setBoard(Board board) {
        this.board = board;
        postInvalidate();
    }
    public void render(){
        postInvalidate();
    }
}
