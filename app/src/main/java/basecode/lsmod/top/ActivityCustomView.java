package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.customui.ScannerItemView;

/**
 * Author:yanfulei
 * Date:2018/12/3
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityCustomView extends BaseActivityTitle {

    @BindView(R.id.siv_scanner1)
    ScannerItemView sivScanner1;

    @Override
    public String setStatusBarColor() {
        return "#3F51B5";
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_left_right_view, null);
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    private void initData() {
        sivScanner1.et_input.requestFocus();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }
}
