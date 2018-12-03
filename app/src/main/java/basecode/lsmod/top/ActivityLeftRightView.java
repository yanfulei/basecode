package basecode.lsmod.top;

import android.view.View;

import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/3
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityLeftRightView extends BaseActivityTitle {
    @Override
    public void initSingle() {

    }

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_left_right_view, null);
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    public void initData() {

    }
}
