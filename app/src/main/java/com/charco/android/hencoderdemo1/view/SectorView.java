package com.charco.android.hencoderdemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created 18/7/16 18:50
 * Author:charcolee
 * Version:V1.0
 * ----------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------
 */

public class SectorView extends View{

    private Paint mPaint;
    private RectF mRectF;

    public SectorView(Context context) {
        this(context,null);
    }

    public SectorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SectorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);

        int mRadius = Util.dp2px(getContext(), 100);

        mRectF = new RectF(-mRadius,-mRadius, mRadius, mRadius);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);

        mPaint.setColor(Color.parseColor("#4e8d28"));

        canvas.drawArc(mRectF,300,60,true,mPaint);

        mPaint.setColor(Color.parseColor("#0c73d4"));

        canvas.drawArc(mRectF,0,50,true,mPaint);

        mPaint.setColor(Color.parseColor("#8151c5"));

        canvas.drawArc(mRectF,50,100,true,mPaint);

        canvas.translate(-20,-20);

        mPaint.setColor(Color.parseColor("#da2827"));

        canvas.drawArc(mRectF,150,150,true,mPaint);

        super.onDraw(canvas);
    }
}
