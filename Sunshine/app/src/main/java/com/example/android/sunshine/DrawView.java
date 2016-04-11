package com.example.android.sunshine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Diamond Ravi on 4/8/2016.
 */
public class DrawView extends View {
    Paint linePaint = new Paint();
    Paint circlePaint = new Paint();
    Paint textPaint = new Paint();
    Paint transPaint = new Paint();
    int x, y;
    final private int P_ROBOT = 30, L_ROBOT = 90;
    final private int X_MID = 300, Y_MID = 435;
    Rect rect;

    public DrawView(Context context) {
        super(context);

        //line properties
        linePaint.setAntiAlias(true);
        linePaint.setColor(R.color.ungu);
        linePaint.setStrokeWidth(10);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setShadowLayer(28, 0, 0, Color.BLACK);

        //circle properties
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.LTGRAY);
        circlePaint.setStyle(Paint.Style.FILL);

        //text Properties
        textPaint.setTextSize(20);
        textPaint.setColor(Color.LTGRAY);

        transPaint.setAntiAlias(true);
        transPaint.setColor(Color.BLACK);
        transPaint.setAlpha(100);
        //rectangle
        rect = new Rect(X_MID - (L_ROBOT / 2), Y_MID - (P_ROBOT / 2),
                X_MID + 1 + (L_ROBOT / 2), Y_MID + 1 + (P_ROBOT / 2));

        x = X_MID;
        y = Y_MID;
    }

    public void setPosisi(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.invalidate();
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            x = (int) event.getX() > 12 && (int) event.getX() < 586 ? (int) event.getX() : x;
            y = (int) event.getY() > 14 && (int) event.getY() < 937 ? (int) event.getY() : y;
        }
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {

        if(x > 0 && y > 0) {
            int x_text = x, y_text;
            if (y < 60 || (y > Y_MID && y < 907 )){
                y_text = y+40;
            } else {
                y_text = y-30;
            }

            if (x > 560){
                textPaint.setTextAlign(Paint.Align.RIGHT);
            } else if (x < 35) {
                textPaint.setTextAlign(Paint.Align.LEFT);
            } else {
                textPaint.setTextAlign(Paint.Align.CENTER);
            }

            String pos = "(" + (x-X_MID) + ", " + ((Y_MID-y)) + ")";
            canvas.drawLine(X_MID, Y_MID, x, y, linePaint);
            canvas.drawCircle(x, y, 16, transPaint);
            canvas.drawCircle(x, y, 12, circlePaint);
            canvas.drawText(pos, x_text, y_text, textPaint);
        }
        canvas.drawCircle(X_MID, Y_MID, 30, circlePaint);
        //canvas.drawRect(rect, circlePaint);

    }

}