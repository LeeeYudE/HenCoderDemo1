package com.charco.android.hencoderdemo1.view;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created 18/7/16 16:42
 * Author:charcolee
 * Version:V1.0
 * ----------------------------------------------------
 * 文件描述：
 * ----------------------------------------------------
 */

public class Util {

    /**
     * dip-->px
     */
    public static int dp2px(Context context , float dip) {
        // px/dip = density;
        // density = dpi/160
        // 320*480 density = 1 1px = 1dp
        // 1280*720 density = 2 2px = 1dp
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    /**
     * sp-->px
     */
    public static int sp2px(Context context , int sp) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics()) + 0.5f);
    }



}
