package top.lsmod.me.basecode.base;

import android.app.Application;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Author:yanfulei
 * Date:2018/11/6
 * Email:yanfulei1990@gmail.com
 **/
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化日志信息
        Logger.addLogAdapter(new AndroidLogAdapter());
    }
}
