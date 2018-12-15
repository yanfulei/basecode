package basecode.lsmod.top;

import android.view.View;

import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/10
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityUi extends BaseActivityTitle {
    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_ui, null);
    }

    @Override
    public String setTitleBarText() {
        return "常用控件";
    }

    @Override
    public boolean showRightTitleButton() {
        return false;
    }
}
