package com.charco.android.hencoderdemo1.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.charco.android.hencoderdemo1.R;

/**
 * Created 18/7/16 20:32
 * Author:charcolee
 * Version:V1.0
 * ----------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------
 */

public class ImageTextView extends View {

    static final String text = "叶凡：小说主角，与众老同学在泰山聚会时一同被九龙拉棺带离地球，" +
            "进入北斗星域，得知自己是荒古圣叶凡 叶凡体。历险禁地，习得源术，斗圣地世家，战太古生物，"+
            "重组天庭，叶凡辗转四方得到许多际遇和挑战，功力激增，眼界也渐渐开阔。一个浩大的仙侠世界，"+
            "就以他的视角在读者面前展开。姬紫月：姬家小姐，出场年龄十七岁。被叶凡劫持一同经历青铜古殿历险，"+
            "依靠碎裂的神光遁符解除禁制，反过来挟持叶凡一同进入太玄派寻找秘术。"+
            "在叶凡逃离太玄后姬紫月在孔雀王之乱中被华云飞追杀，又与叶凡[2]相遇，被叶凡护送回姬家"+
            "，渐渐对叶凡产生微妙感情。后成为叶凡的妻子，千载后于飞仙星成仙，在叶凡也进入仙路后再见庞博："+
            "叶凡大学时最好的朋友，壮硕魁伟，直率义气。到达北斗星域后因服用了圣果被灵墟洞天作为仙苗，"+
            "在青帝坟墓处为青帝十九代孙附体离去，肉身被锤炼至四极境界。后叶凡与黑皇镇压老妖神识，"+
            "庞博重新掌控自己身躯，取得妖帝古经和老妖本体祭炼成的青莲法宝，习得妖帝九斩和天妖八式，"+
            "但仍伪装成老妖留在妖族。出关后找上叶凡，多次与他共进退。星空古路开启后由此离开北斗，"+
            "被叶凡从妖皇墓中救出，得叶凡授予者字秘、一气化三清，与叶凡同闯试炼古路，一起建设天庭";

    private Paint mPaint;
    private Bitmap mBitmap;
    //一行可以最多可以显示的文字个数
    private int mOneLineTextCount;
    //当有图片的时候显示的文字个数
    private int mImageLineTextCount;
    //文字行高
    private int mTextHeight;
    //图片Y坐标
    private int mImageY;

    public ImageTextView(Context context) {
        this(context,null);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ImageTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        int widthPixels = getContext().getResources().getDisplayMetrics().widthPixels;
        int textSize = Util.sp2px(getContext(), 16);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(textSize);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);

        Paint.FontMetricsInt fm = mPaint.getFontMetricsInt();
        //获取行高
        mTextHeight = Math.abs((fm.ascent - fm.descent));
        //简单的认为view宽度为屏幕宽度，一行能绘制的文字个数为view宽度/文字宽度
        mOneLineTextCount = widthPixels / textSize;
        //当有图片的时候，一行能绘制的文字个数为（view宽度－图片宽度）／文字宽度
        mImageLineTextCount = (widthPixels - mBitmap.getWidth())/ textSize;

        mImageY = Util.dp2px(getContext(),100);

    }
    @Override
    protected void onDraw(Canvas canvas) {
        //已经绘制的文字个数
        int drawTextSize = 0;
        //绘制文字的Y坐标
        int drawTextY = mTextHeight;
        //该绘制的文字个数
        int textCount ;
        while (drawTextSize < text.length()){

            //如果所绘制的文字Y坐标不在图片的区域内，绘制的文字个数就为最大的个数
            if (drawTextY < mImageY || drawTextY > mImageY + mBitmap.getHeight() + mTextHeight){
                textCount = mOneLineTextCount;
            }else {//否则就文字个数为计算好的偏差个数
                textCount = mImageLineTextCount;
            }
            //防止string切割的越界
            if (text.length() - drawTextSize < textCount){
                textCount = text.length() - drawTextSize ;
            }
            //绘制文字
            canvas.drawText(text.substring(drawTextSize,drawTextSize + textCount),0,drawTextY,mPaint);
            //文字绘制完，需要增加已绘制的数量
            drawTextSize += textCount;
            //绘制的Y也需要增加
            drawTextY += mTextHeight;

        }

        //绘制图片
        canvas.drawBitmap(mBitmap,getWidth()-mBitmap.getWidth(),mImageY,mPaint);

        super.onDraw(canvas);
    }
}
