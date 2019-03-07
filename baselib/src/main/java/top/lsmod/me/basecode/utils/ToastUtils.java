package top.lsmod.me.basecode.utils;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by yanfulei on 2018/10/1
 * Email yanfulei1990@gmail.com
 */
public class ToastUtils {

    public static int ERROR = 1;
    public static int INFO = 2;
    public static int WARNING = 3;
    public static int SUCCESS = 4;

    /**
     * 展示Toast
     *
     * @param ctx
     * @param msg
     * @param state
     */
    public static void showToast(final Activity ctx, final String msg, int state) {
        // 判断是在子线程，还是主线程
        if ("main".equals(Thread.currentThread().getName())) {
            swichState(ctx, msg, state);
        } else {
            // 子线程
            ctx.runOnUiThread(() -> swichState(ctx, msg, state));
        }
    }


    private static void swichState(Activity ctx, String msg, int stata) {
        if (stata == ERROR) {
            TastyToast.makeText(ctx, msg, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
            return;
        }
        if (stata == INFO) {
            TastyToast.makeText(ctx, msg, TastyToast.LENGTH_SHORT, TastyToast.INFO);
            return;
        }
        if (stata == WARNING) {
            TastyToast.makeText(ctx, msg, TastyToast.LENGTH_SHORT, TastyToast.WARNING);
            return;
        }
        if (stata == SUCCESS) {
            TastyToast.makeText(ctx, msg, TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
            return;
        }
    }

    /**
     * 展示Snackbar
     * @param ctx
     * @param view
     * @param msg
     */
    public static void showSnackbar(Activity ctx, View view, final String msg){
        // 判断是在子线程，还是主线程
        if ("main".equals(Thread.currentThread().getName())) {
            Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        } else {
            // 子线程
            ctx.runOnUiThread(() ->
                    Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setAction("Action", null).show());
        }
    }
}
