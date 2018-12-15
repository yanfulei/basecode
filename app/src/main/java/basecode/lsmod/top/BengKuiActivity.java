package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;

import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/15
 * Email:yanfulei1990@gmail.com
 **/
public class BengKuiActivity extends BaseActivityTitle {
    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return null;
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    public boolean showRightTitleButton() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        throw new NullPointerException();
    }
}
