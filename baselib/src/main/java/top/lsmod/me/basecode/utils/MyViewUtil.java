package top.lsmod.me.basecode.utils;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

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
}
