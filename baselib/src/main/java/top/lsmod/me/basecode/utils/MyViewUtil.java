package top.lsmod.me.basecode.utils;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: 一些view工具栏
 * @date: 2019/3/19 5:44 PM
 */
public class MyViewUtil {

    /**
     * view变大特效
     *
     * @param view
     */
    public static void viewBigAnimation(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation animation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animationSet.addAnimation(animation);
        animationSet.setFillAfter(true);
        view.clearAnimation();
        view.startAnimation(animationSet);
    }

    /**
     * view变小动画
     *
     * @param view
     */
    public static void viewSmallAnimation(View view) {
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation animation = new ScaleAnimation(1.1f, 1.0f, 1.1f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(500);
        animation.setFillAfter(true);
        animationSet.addAnimation(animation);
        animationSet.setFillAfter(true);
        view.startAnimation(animationSet);
    }

    /**
     * 设置布局margin
     *
     * @param v
     * @param l
     * @param t
     * @param r
     * @param b
     */
    public static void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    /**
     * 获取手机状态栏的高度
     *
     * @return 状态栏的高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, int pxValue) {
        return ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, pxValue, context.getResources().getDisplayMetrics()));
    }

    /**
     * 广度遍历
     * 获取所有自view
     *
     * @param rootView
     */
    public static List<View> depthTravelView(View rootView) {
        List<View> list = new ArrayList<>();
        ArrayDeque queue = new ArrayDeque<>();
        queue.addLast(rootView);
        while (!queue.isEmpty()) {
            View temp = (View) queue.getLast();
            //队尾出队
            queue.pollLast();
            if (temp instanceof ViewGroup) {
                int childCount = ((ViewGroup) temp).getChildCount();
                for (int i = childCount - 1; i >= 0; i--) {
                    queue.addLast(((ViewGroup) temp).getChildAt(i));
                }
            }
            list.add(temp);
        }
        return list;
    }

    /**
     * 获取图片资源ID
     *
     * @param imageView
     * @return
     */
    public static int getImageViewResourcesId(ImageView imageView) {
        Field[] fields = imageView.getClass().getDeclaredFields();
        int imgid = 0;
        for (Field f : fields) {
            if (f.getName().equals("mResource")) {
                f.setAccessible(true);
                try {
                    imgid = f.getInt(imageView);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return imgid;
    }
}
