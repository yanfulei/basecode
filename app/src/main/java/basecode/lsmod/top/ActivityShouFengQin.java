package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.cachapa.expandablelayout.ExpandableLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/19
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityShouFengQin extends BaseActivityTitle {
    @BindView(R.id.btn_zhankai)
    Button btnZhankai;
    @BindView(R.id.expandable_layout)
    ExpandableLayout expandableLayout;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_shoufengqin, null);
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    public boolean showRightTitleButton() {
        return false;
    }

    @OnClick({R.id.btn_zhankai})
    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.btn_zhankai:
                if (expandableLayout.isExpanded()) {
                    expandableLayout.collapse();
                } else {
                    expandableLayout.expand();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
