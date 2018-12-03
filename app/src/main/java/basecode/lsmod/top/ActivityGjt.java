package basecode.lsmod.top;

import android.view.View;
import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/2
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityGjt extends BaseActivityTitle {
    private View view;

    @Override
    public void initSingle() {
    }

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        view = getLayoutInflater().inflate(R.layout.activity_gjt, null);
        return view;
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    public void initData() {

    }
}
