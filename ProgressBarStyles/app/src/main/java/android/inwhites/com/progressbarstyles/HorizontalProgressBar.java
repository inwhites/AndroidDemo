package android.inwhites.com.progressbarstyles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by inwhites on 2016/10/25.
 */

public class HorizontalProgressBar extends View {

    private int centerX ;
    private int centerY ;

    private int progresslength  = 20;

    private int horColor = 0xFFC7A07B;
    private int progressColor = 0xFF26FF5E;
    private int fontColor = 0xFF3E9BF3;
    private int fontSize = 30;
    private int progress = 0;
   private int roundRect = 20;
    private Paint mPaint;




    public HorizontalProgressBar(Context context) {
        this(context,null);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HorizontalProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        centerX = getWidth()/2;
        centerY = getHeight()/2;

        drawHorProgressBar(canvas,mPaint);



    }



    public void drawHorProgressBar(Canvas canvas,Paint paint){
        //圆角矩形
        RectF oval1 = new RectF(centerX-getWidth()/2,centerY - getHeight()/2,centerX+getWidth()/2,
                centerY+getHeight()/2);
        paint.setColor(horColor);
        canvas.drawRoundRect(oval1,roundRect,roundRect,paint);



        //画进度条
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.FILL);
        RectF oval2 = new RectF(centerX-getWidth()/2,centerY - getHeight()/2,(progress*getWidth())/100,
                centerY+getHeight()/2);
        canvas.drawRoundRect(oval2,roundRect,roundRect,paint);


        fontSize = (int )oval1.height();
        paint.setColor(fontColor);
        paint.setTextSize(fontSize);
        String text = progress + "%";
        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float baseLine = (getMeasuredHeight() - fontMetrics.bottom+fontMetrics.top)/2 -fontMetrics.top;
        canvas.drawText(text,centerX-textWidth/2,baseLine,paint);

    }


    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setHorColor(int horColor) {
        this.horColor = horColor;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setProgresslength(int progresslength) {
        this.progresslength = progresslength;
    }

    public void setRoundRect(int roundRect) {
        this.roundRect = roundRect;
    }
}
