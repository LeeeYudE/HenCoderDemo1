package com.charco.android.hencoderdemo1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.charco.android.hencoderdemo1.R;

/**
 * Created 18/7/16 19:24
 * Author:charcolee
 * Version:V1.0
 * ----------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------
 */

public class AvatarView extends View {

    private Paint mBitmapPaint , mPaint;
    private Bitmap mBitmap;
    private float mPaintWidth;

    public AvatarView(Context context) {
        this(context,null);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AvatarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //绘制图片的paint
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //绘制圆环的paint
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.colorPrimary));
        mPaintWidth = Util.dp2px(getContext(),5);
        mPaint.setStrokeWidth(mPaintWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);

        Shader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP,Shader.TileMode.CLAMP);

        mBitmapPaint.setShader(shader);

    }

    @Override
    public void draw(Canvas canvas) {

        //绘制带image的圆，中心点为view的中心
        canvas.drawCircle(mBitmap.getWidth()/2,getWidth()/2,getHeight()/2,mBitmapPaint);

        //绘制黑色圆边框，中心点为view的中心
        canvas.drawCircle(mBitmap.getWidth()/2,getWidth()/2,getHeight()/2 - mPaintWidth/2,mPaint);

        super.draw(canvas);
    }

    //设置view的宽高为图片宽高
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(mBitmap.getWidth(), mBitmap.getHeight());
    }

}
