package com.ske.snakebaddesign.game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.graphics.Paint;
import android.util.Log;

import com.ske.snakebaddesign.game.Player;

/**
 * Created by WIT on 16-Mar-16.
 */
public class Board {
    private PlayerInterface p1;
    private PlayerInterface p2;
// Graphics variables
    private Paint paint;
    private float viewWidth;
    private float cellSize;
    private float padding;
    private float playerSize;
    private int colorBG = Color.parseColor("#6d8d46");
    private int colorCell = Color.parseColor("#87aa4c");
    private int colorText = Color.parseColor("#cfe8a6");
    private int colorP1 = Color.parseColor("#fc3936");
    private int colorP2 = Color.parseColor("#59cfdf");
    private int colorP1P2 = Color.parseColor("#59dfac");

    // These variables will be used to keep track of what to render
    private int size;

    public Board(Player p1, Player p2, int size) {
        this.p1 = p1;
        this.p2 = p2;
        this.size = size;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(20);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    public void drawBoard(Canvas canvas) {
        paint.setColor(colorBG);
        canvas.drawRect(0, 0, viewWidth, viewWidth, paint);
    }

    public void drawSquares(Canvas canvas,Context context) {

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                float startX = i * cellSize + padding/2;
                float startY = j * cellSize + padding/2;
                float endX = startX + cellSize - padding;
                float endY = startY + cellSize - padding;

                if(p1.getPosition()==j*size+i&&p2.getPosition()==j*size+i){
                    paint.setColor(colorP1P2);
                }
                else if(p1.getPosition()==j*size+i){
                    paint.setColor(colorP1);
                }
                else if(p2.getPosition()==j*size+i){
                    paint.setColor(colorP2);
                }
                else{
                    paint.setColor(colorCell);
                }
                canvas.drawRect(startX, startY, endX, endY, paint);
                paint.setColor(colorText);
                String label = (j * size + i + 1) + "";
                canvas.drawText(label, startX + cellSize/2 - padding/2, startY + cellSize/2, paint);

            }
        }
    }

    public void drawPlayerPieces(Canvas canvas) {
        // Draw player 1 (0.33 is the 1/3 position of the cell height)
        paint.setColor(p1.getColor());
        float p1X = positionToCol(p1.getPosition()) * cellSize + cellSize/2;
        float p1Y = positionToRow(p1.getPosition()) * cellSize + (cellSize * 0.33f);
        canvas.drawCircle(p1X, p1Y, playerSize, paint);
        // Draw player 2 (0.66 is the 2/3 position of the cell height)
        paint.setColor(p2.getColor());
        float p2X = positionToCol(p2.getPosition()) * cellSize + cellSize/2;
        float p2Y = positionToRow(p2.getPosition()) * cellSize + (cellSize * 0.66f);
        canvas.drawCircle(p2X, p2Y, playerSize, paint);
    }

    public void reloadViewDimensions(int width) {
        viewWidth = width;
        cellSize = (viewWidth / size);
        padding = 0.05f * cellSize;
        playerSize = cellSize/8;
    }
    private int positionToRow(int position) {
        return position / size;
    }

    private int positionToCol(int position) {
        return position % size;
    }
}
