package dulife.clps.com.dulife_version_01.util;

import android.content.Context;

/**
 * Created by ${zhangyanfu} on 2016/10/5.
 * Email : zhangyanfu66@gmail.com
 */

public class ToolUtil {
    public static final int getHeightInPx(Context context) {
        final int height = context.getResources().getDisplayMetrics().heightPixels;
        return height;
    }

    public static final int getWidthInPx(Context context) {
        final int width = context.getResources().getDisplayMetrics().widthPixels;
        return width;
    }

    public static final int getWidthInDp(Context context) {
        final float width = context.getResources().getDisplayMetrics().widthPixels;
        int widthInDp = px2dip(context, width);
        return widthInDp;
    }

    public static final int getHeightInDp(Context context) {
        final float height = context.getResources().getDisplayMetrics().widthPixels;
        int heightInDp = px2dip(context, height);
        return heightInDp;
    }

    public static final int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static final int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static final int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static final int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }



    /**
     * //获取状态栏的高度
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {

            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object obj = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(obj).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);


        } catch (Exception e) {

        }
        return statusHeight;
    }
}
