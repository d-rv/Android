package mechatronics.demon.bb_2;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by Diamond Ravi on 4/8/2016.
 */
public class DrawView extends View {

    final private int P_ROBOT = 30, L_ROBOT = 90;
    final private int X_MID = 300, Y_MID = 300;

    private Paint linePaint = new Paint();
    private Paint circlePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint transPaint = new Paint();

    private int x, y;
    private RectF rect;
    private String posStr = null;
    private float rectRotation;

    public DrawView(Context context) {
        super(context);

        //line properties
        linePaint.setAntiAlias(true);
        linePaint.setColor(R.color.colorPrimaryDark);
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
        transPaint.setColor(Color.parseColor("#FFFFDA44"));
        transPaint.setAlpha(100);

        //rectangle
        rect = new RectF(X_MID - (L_ROBOT / 2), Y_MID - (P_ROBOT / 2),
                X_MID + 1 + (L_ROBOT / 2), Y_MID + 1 + (P_ROBOT / 2));

        x = X_MID;
        y = Y_MID;

        rectRotation = 0f;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getViewX(){
        return x;
    }

    public int getViewY(){
        return y;
    }

    public String getPosStr(){
        return posStr;
    }

    public void setRotation(float degree){
        rectRotation = degree;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = 0;
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();

        if (width > height) {
            size = height;
        } else {
            size = width;
        }
        setMeasuredDimension(size, size);
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(x > 0 && y > 0) {
            int x_text = x, y_text;
            if (y < 60 || (y > Y_MID && y < 557 )){
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

            posStr = "(" + (x-X_MID) + ", " + (Y_MID-y) + ")";
            canvas.drawLine(X_MID, Y_MID, x, y, linePaint);
            canvas.drawCircle(x, y, 16, transPaint);
            canvas.drawCircle(x, y, 12, circlePaint);
            canvas.drawText(posStr, x_text, y_text, textPaint);
        }


        //draw rotated rectangle
        canvas.rotate(rectRotation, X_MID, Y_MID);
        canvas.drawRect(rect, circlePaint);
        canvas.rotate(-rectRotation, X_MID, Y_MID);

    }
}