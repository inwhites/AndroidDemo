package android.inwhites.com.progressbarstyles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by inwhites on 2016/10/26.
 */

public class SectorProgressBar extends View {

    private int centerX ;
    private int centerY ;
    private int radius  = 20;
    private int strokeWidth = 3;
    private int roundColor = 0xFFC7A07B;
    private int progressColor = 0xFF26FF5E;
    private int fontColor = 0xFF3E9BF3;
    private int fontSize = 30;
    private int progress = 0;
    private Paint mPaint;


    public SectorProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SectorProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public SectorProgressBar(Context context) {
        this(context,null);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        centerX = getWidth()/2;
        centerY = getHeight()/2;
        radius = centerX >  centerY ?  centerY-strokeWidth:centerX-strokeWidth;
        fontSize = radius / 5;
        drawSectorProgressBar(canvas,mPaint);
    }

    public  void drawSectorProgressBar(Canvas canvas,Paint paint){

        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        canvas.drawCircle(centerX,centerY,radius,paint);

        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.FILL);
        RectF oval = new RectF(centerX-radius,centerY-radius,centerX+radius,centerY+radius);
        canvas.drawArc(oval,-90,360*progress/100,true,paint);

        paint.setColor(fontColor);
        paint.setTextSize(fontSize);

        String text = progress + "%";
        Rect rect = new Rect();

        paint.getTextBounds(text,0,text.length(),rect);
        int textWidth = rect.width();
        int textheight = rect.height();
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float baseLine = (getMeasuredHeight() - fontMetrics.bottom+fontMetrics.top)/2 - fontMetrics.top;

        canvas.drawText(text,centerX-textWidth/2,baseLine,paint);

    }


    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public void setFontColor(int fontColor) {
        this.fontColor = fontColor;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
    }

    public void setProgress(int progress) {
        this.progress = progress;
        postInvalidate();
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}
