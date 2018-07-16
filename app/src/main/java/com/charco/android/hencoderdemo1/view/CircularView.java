package com.charco.android.hencoderdemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created 18/7/16 19:01
 * Author:charcolee
 * Version:V1.0
 * ----------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------
 */

public class CircularView extends View {

    private Paint mPaint;
    private TextPaint mTextPaint;
    private float mRadius;
    private RectF mRectF;

    public CircularView(Context context) {
        this(context,null);
    }

    public CircularView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircularView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //绘制
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(Util.dp2px(getContext(),10));
        //绘制文字的paint
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(Util.sp2px(getContext(),70));
        mTextPaint.setColor(Color.parseColor("#f12761"));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mRadius = Util.dp2px(getContext(), 100);

        mRectF  = new RectF(-mRadius, -mRadius, mRadius, mRadius);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //将坐标轴中心点设置为view的中心
        canvas.translate(getWidth()/2,getHeight()/2);

        mPaint.setColor(Color.parseColor("#8fa4af"));
        //绘制内层圆环
        canvas.drawArc(mRectF,0,360,false,mPaint);

        mPaint.setColor(Color.parseColor("#f12761"));
        //绘制外层圆环
        canvas.drawArc(mRectF,270,220,false,mPaint);

        //绘制文字
        //文字垂直居中显示  参考 https://blog.csdn.net/sinat_26710701/article/details/70184252
        Paint.FontMetricsInt fm = mTextPaint.getFontMetricsInt();
        canvas.drawText("abcd",0,-(fm.descent - (-fm.ascent + fm.descent)/2),mTextPaint);

        super.onDraw(canvas);

    }
}
