package com.charco.android.hencoderdemo1.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.charco.android.hencoderdemo1.R;


/**
 * Created 18/7/16 16:39
 * Author:charcolee
 * Version:V1.0
 * ----------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------
 */

public class ScaleView extends View {

    //参考博客 https://blog.csdn.net/u013831257/article/details/51565591

    private Paint mPaint;
    private float mPaintWidth;
    private Path mPath;
    private PathMeasure mPathMeasure , mInsidePathMeasure;
    private float[] mOutsidePos ,mInsidePos , tan;

    public ScaleView(Context context) {
        this(context,null);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mPaintWidth = Util.dp2px(getContext(),1.5f);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setColor(getResources().getColor(R.color.black));
        mPaint.setStrokeWidth(mPaintWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        int outsideRadius = Util.dp2px(getContext(), 100);

        RectF mRectF = new RectF(-outsideRadius, -outsideRadius, outsideRadius, outsideRadius);

        mPath = new Path();

        mPath.addArc(mRectF,150,240);

        mPathMeasure = new PathMeasure(mPath,false);

        int insideRadius = Util.dp2px(getContext(), 95);

        RectF rectF = new RectF(-insideRadius, -insideRadius, insideRadius, insideRadius);

        Path path = new Path();
        path.addArc(rectF,150,240);

        mInsidePathMeasure = new PathMeasure(path,false);

        mOutsidePos = new float[2];
        mInsidePos = new float[2];
        tan = new float[2];

    }


    @Override
    protected void onDraw(Canvas canvas) {

        canvas.translate(getWidth()/2,getHeight()/2);
        //绘制圆环
        canvas.drawPath(mPath,mPaint);


        float distance = 0;

        float distanceLength ;

        while (distance <= 1f){

            distanceLength = mPathMeasure.getLength() * distance;

            if (distance == 0 ){
                //第一个刻度需要增加paintWidth的一半宽度，免得边缘有间隙
                distanceLength += mPaintWidth/2;
            }

            mPathMeasure.getPosTan( distanceLength , mOutsidePos , tan );

            distanceLength = mInsidePathMeasure.getLength() * distance;

            mInsidePathMeasure.getPosTan(distanceLength,mInsidePos,tan);

            canvas.drawLine(mInsidePos[0],mInsidePos[1],mOutsidePos[0],mOutsidePos[1],mPaint);

            distance += 0.05f;

            //由于float会有精度偏差，当distance＝1.00000005f,暂时用这种简陋的方法解决
            if (distance >1f){
                distance = 1f;
                distanceLength = mInsidePathMeasure.getLength() * distance;
                mInsidePathMeasure.getPosTan(distanceLength,mInsidePos,tan);
                //最后一个需要减去paintWidth的一半宽度，免得边缘有间隙
                mPathMeasure.getPosTan(mPathMeasure.getLength() * distance - mPaintWidth/2, mOutsidePos , tan );
                canvas.drawLine(mInsidePos[0],mInsidePos[1],mOutsidePos[0],mOutsidePos[1],mPaint);
                break;
            }

        }

        super.onDraw(canvas);
    }
}
