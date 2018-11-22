package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.flyco.animation.BounceEnter.BounceTopEnter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.ui.ShareBottomDialog;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Author:yanfulei
 * Date:2018/11/22
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityDialog extends BaseActivityTitle {
    @BindView(R.id.btn_share_buttom_dialog)
    Button btnShareButtomDialog;

    @Override
    public void initSingle() {

    }

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_dialog, null);
    }

    @Override
    public String setTitleBarText() {
        return "弹出框";
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_share_buttom_dialog, R.id.btn_share_buttom_dialog_nodel})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_share_buttom_dialog:
                List<String> datas = new ArrayList<>();
                datas.add("test1");
                datas.add("test2");
                datas.add("test3");
                BounceTopEnter mBasIn = new BounceTopEnter();
                final ShareBottomDialog dialog = new ShareBottomDialog(this, getLlAllView(), datas, true);
                dialog.showAnim(mBasIn).show();
                dialog.setOnItemClickListener((adapterView, view1, i, l) -> ToastUtils.showToast(ActivityDialog.this, "点击位置为【" + i + "】", ToastUtils.SUCCESS));
                dialog.setOnItemDelClickListener(postion -> ToastUtils.showToast(ActivityDialog.this, "删除位置为【" + postion + "】", ToastUtils.SUCCESS));
                break;
            case R.id.btn_share_buttom_dialog_nodel:
                List<String> datas1 = new ArrayList<>();
                datas1.add("test1");
                datas1.add("test2");
                datas1.add("test3");
                BounceTopEnter mBasIn1 = new BounceTopEnter();
                final ShareBottomDialog dialog1 = new ShareBottomDialog(this, getLlAllView(), datas1, false);
                dialog1.showAnim(mBasIn1).show();
                dialog1.setOnItemClickListener((adapterView, view1, i, l) -> ToastUtils.showToast(ActivityDialog.this, "点击位置为【" + i + "】", ToastUtils.SUCCESS));
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
