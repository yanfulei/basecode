package basecode.lsmod.top;

import android.view.View;

import top.lsmod.me.basecode.base.BaseActivitySearchTitle;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Author:yanfulei
 * Date:2018/11/29
 * Email:yanfulei1990@gmail.com
 **/
public class ActicitySearchTitle extends BaseActivitySearchTitle {
    @Override
    public void onTitleSearch(String key) {
        ToastUtils.showToast(this, key, ToastUtils.INFO);
    }

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_search_title, null);
    }
}
