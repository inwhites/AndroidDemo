package android.inwhites.com.progressbarstyles;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by inwhites on 2016/10/23.
 */

public class RoundProgressBar extends View {

    private int centerX ;
    private int centerY ;
    private int radius  = 20;
    private int strokeWidth = 30;
    private int roundColor = 0xFFC7A07B;
    private int progressColor = 0xFF26FF5E;
    private int fontColor = 0xFF3E9BF3;
    private int fontSize = 10;
    private int progress = 0;
    private Paint mPaint;

    public RoundProgressBar(Context context) {
        this(context,null);


    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);


    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    }



    @Override
    protected void onDraw(Canvas canvas) {

        centerX = getWidth()/2;
        centerY = getHeight()/2;


        fontSize = strokeWidth+10;
        radius  = centerX < centerY ? centerX-strokeWidth:centerY - strokeWidth;
        drawRoundProgressBar(canvas,mPaint);
    }

    private void drawRoundProgressBar(Canvas canvas, Paint paint){
      //  paint.setColor(roundColor);
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);

        //draw round
        canvas.drawCircle(centerX,centerY,radius,paint);
        //draw progress
        paint.setColor(progressColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        RectF oval = new RectF(centerX - radius, centerY - radius, radius + centerX, radius + centerY);
        canvas.drawArc(oval,-90,360*progress/100,false,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(fontColor);
        paint.setTextSize(fontSize);
        String text = progress + "%";

        Rect rect = new Rect();
        paint.getTextBounds(text,0,text.length(),rect);
        float textWidth = rect.width();
        float textHeight = rect.height();

        Paint.FontMetrics metrics = paint.getFontMetrics();
        float baseline = (getMeasuredHeight()-metrics.bottom+metrics.top)/2-metrics.top;
        float baseline1 = (getMeasuredHeight()+metrics.bottom-metrics.top)/2-metrics.bottom;
        Log.d("TAG", "drawRoundProgressBar: " + baseline);
        Log.d("TAG", "drawRoundProgressBar: " + baseline1);
        Log.d("TAG", "drawRoundProgressBar: " + metrics.ascent+ "   "+ metrics.descent);
        canvas.drawText(text, centerX - textWidth / 2, baseline, paint);




    }

    public void setProgress(int progress) {
        if(progress >100)
            progress = 0;
        this.progress = progress;
        postInvalidate();

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

    public void setPaint(Paint paint) {
        mPaint = paint;
    }

    public void setProgressColor(int progressColor) {
        this.progressColor = progressColor;
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
