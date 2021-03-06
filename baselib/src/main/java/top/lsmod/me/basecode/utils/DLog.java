package top.lsmod.me.basecode.utils;

import android.util.Log;


/**
 * log管理类
 *
 * @author huangminzheng
 */
public class DLog {

    public static boolean isLog = true;
    public static String TAG = "hmz";

    public static void setIsLog(boolean isLog) {
        DLog.isLog = isLog;
    }

    public static void setTAG(String TAG) {
        DLog.TAG = TAG;
    }

    public static void v(String tag, String msg) {
        if (isLog) {
            Log.v(tag, msg);
        }
    }


    public static void d(String tag, String msg) {
        if (isLog) {
            Log.d(tag, msg);
        }
    }

    public static void d(String msg) {
        if (isLog) {
            Log.d(TAG, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (isLog) {
            Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (isLog) {
            Log.d(TAG, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isLog) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isLog) {
            Log.e(tag, msg);
        }
    }

    public static void e(String msg) {
        if (isLog) {
            Log.e(TAG, msg);
        }
    }

    public static void e(String tag, Throwable throwable) {
        if (isLog) {
            Log.e(tag, throwable.getMessage(), throwable);
        }
    }

    public static void i(String tag, String msg, Throwable throwable) {
        if (isLog) {
            Log.i(tag, msg, throwable);
        }
    }

    public static void w(String tag, String msg, Throwable throwable) {
        if (isLog) {
            Log.w(tag, msg, throwable);
        }
    }

    public static void e(String tag, String msg, Throwable throwable) {
        if (isLog) {
            Log.e(tag, msg, throwable);
        }
    }
}
