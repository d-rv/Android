package mechatronics.demon.bb_2;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Diamond Ravi on 4/8/2016.
 */
public class DrawView extends View {

    final private int P_ROBOT = 30, L_ROBOT = 90;
    private int X_MID;
    private int Y_MID;

    private Paint linePaint = new Paint();
    private Paint circlePaint = new Paint();
    private Paint textPaint = new Paint();
    private Paint transPaint = new Paint();

    private int x, y, size;
    private RectF rect,rect2;
    private String posStr = null;
    private float rectRotation;

    public DrawView(Context context) {
        super(context);

        //line properties
        linePaint.setAntiAlias(true);
        linePaint.setColor(R.color.colorPrimaryDark);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        linePaint.setShadowLayer(28, 0, 0, Color.BLACK);
        linePaint.setAlpha(100);

        //circle properties
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(Color.DKGRAY);
        circlePaint.setStyle(Paint.Style.FILL);

        //text Properties
        textPaint.setTextSize(20);
        textPaint.setColor(Color.DKGRAY);

        transPaint.setAntiAlias(true);
        transPaint.setColor(Color.parseColor("#FF009688"));
        transPaint.setAlpha(100);

        //rectangle

        rectRotation = 0f;
    }

    public void setPos(int x_bot, int y_bot) {
        int margin = size-16;
        if (x_bot < 15) {
            x_bot = 15;
        } else if (x > size) {
            x_bot = margin;
        }

        if (y_bot < 15) {
            y_bot = 15;
        } else if (y_bot > margin) {
            y_bot = margin;
        }
        this.x = x_bot;
        this.y = y_bot;
    }

    public void moveCursor(int x, int y){
        setPos(x+X_MID,Y_MID-y);
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

        super.onMeasure(widthMeasureSpec,  heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        size = 0;

        if (width > height) {
            size = height;
        } else {
            size = width;
        }

        //set canvas dimension
        setMeasuredDimension(size,size);

        //set 0 position
        X_MID = size/2;
        Y_MID = X_MID;

        //create a rectangle
        rect = new RectF(X_MID - (L_ROBOT / 2), Y_MID - (P_ROBOT / 2),
                X_MID + 1 + (L_ROBOT / 2), Y_MID + 1 + (P_ROBOT / 2));

        rect2 = new RectF(X_MID - (L_ROBOT / 2)-4, Y_MID - (P_ROBOT / 2)-4,
                X_MID + 1 + (L_ROBOT / 2)+4, Y_MID + 1 + (P_ROBOT / 2)+4);
        x = X_MID;
        y = Y_MID;
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x_text = x, y_text;
        if (y < 80 || (y > Y_MID && y < size-63 )){
            y_text = y+65;
        } else if (y > size-63){
            y_text = y-55;
        } else {
            y_text = y-60;
        }

        if (x > size - 40){
            textPaint.setTextAlign(Paint.Align.RIGHT);
        } else if (x < 35) {
            textPaint.setTextAlign(Paint.Align.LEFT);
        } else {
            textPaint.setTextAlign(Paint.Align.CENTER);
        }

        posStr = (x-X_MID) + ", " + (Y_MID-y);

        // draw circles and text
        canvas.drawLine(X_MID, Y_MID, x, y, linePaint);
        canvas.drawCircle(x, y, 16, transPaint);
        canvas.drawCircle(x, y, 12, circlePaint);
        canvas.drawText(posStr, x_text, y_text, textPaint);

        //draw rotated rectangle
        canvas.rotate(rectRotation, X_MID, Y_MID);
        canvas.drawRect(rect2,transPaint);
        canvas.drawRect(rect, circlePaint);
        canvas.rotate(-rectRotation, X_MID, Y_MID);

    }
}