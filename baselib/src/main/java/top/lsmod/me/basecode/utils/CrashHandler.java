package top.lsmod.me.basecode.utils;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;

import top.lsmod.me.basecode.base.CrashDialogActivity;

/**
 * 功能描述：系统崩溃集中处理类
 * Created by Yanfulei on 2017/11/30.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static CrashHandler instance;
    private Application application;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    /**
     * 保证只有一个CrashHandler实例
     */
    private CrashHandler(Context context) {
        application = (Application) context.getApplicationContext();
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取CrashHandler实例 ,单例模式
     */
    public static CrashHandler getInstance(Context context) {
        CrashHandler inst = instance;
        if (inst == null) {
            synchronized (CrashHandler.class) {
                inst = instance;
                if (inst == null) {
                    inst = new CrashHandler(context.getApplicationContext());
                    instance = inst;
                }
            }
        }
        return inst;
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            ex.printStackTrace(printWriter);
            printWriter.close();
            String unCaughtException = stringWriter.toString();//详细错误日志
            Log.e("崩溃信息", unCaughtException);
            // 提示信息
            Intent intent = new Intent(application, CrashDialogActivity.class);
            CrashDialogActivity.ErrorBean errorBean = new CrashDialogActivity.ErrorBean();
//            errorBean.setALI_SLS_LOG_TYPE(BuildConfig.ALI_SLS_LOG_TYPE);
            errorBean.setErrorMsg(unCaughtException);
//            errorBean.setSN(SystemStatic.PROPERTY_IMSI);
//            errorBean.setStoreName(SystemStatic.BASE_INFO.getDeviceInfo().getStore().getStoreName());
            intent.putExtra("error_bean", errorBean);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            application.startActivity(intent);
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mDefaultHandler.uncaughtException(thread, ex);
    }
}