package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Author:yanfulei
 * Date:2018/11/29
 * Email:yanfulei1990@gmail.com
 **/
public class ActivitySwitchButton extends BaseActivityTitle {
    @BindView(R.id.sb_test)
    SwitchButton sbTest;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_switch_button, null);
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        sbTest.setOnCheckedChangeListener((compoundButton, b) -> ToastUtils.showToast(ActivitySwitchButton.this, "" + b, ToastUtils.INFO));
    }
}
