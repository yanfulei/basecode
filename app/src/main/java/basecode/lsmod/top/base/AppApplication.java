package basecode.lsmod.top.base;

import top.lsmod.me.basecode.base.BaseApplication;
import top.lsmod.me.basecode.utils.CrashHandler;

/**
 * Author:yanfulei
 * Date:2018/11/6
 * Email:yanfulei1990@gmail.com
 **/
public class AppApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance(this);
    }
}
