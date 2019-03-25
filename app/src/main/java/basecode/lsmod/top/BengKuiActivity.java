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
    public String setStatusBarColor() {
        return "#3F51B5";
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        throw new NullPointerException();
    }
}
