package basecode.lsmod.top;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.flyco.dialog.listener.OnBtnClickL;
import com.flyco.dialog.widget.popup.BubblePopup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.customui.dialog.MaterialNumDialog;
import top.lsmod.me.basecode.ui.ShareBottomDialog;
import top.lsmod.me.basecode.ui.SimpleCustomPop;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Author:yanfulei
 * Date:2018/11/22
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityDialog extends BaseActivityTitle {
    @BindView(R.id.btn_share_buttom_dialog)
    Button btnShareButtomDialog;
    @BindView(R.id.btn_share_buttom_dialog_nodel)
    Button btnShareButtomDialogNodel;
    @BindView(R.id.btn_popup_dialog)
    Button btnPopupDialog;

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

    @OnClick({R.id.btn_share_buttom_dialog, R.id.btn_share_buttom_dialog_nodel, R.id.btn_popup_dialog, R.id.btn_ed_dialog})
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
                dialog.setOnItemDelClickListener(postion -> {
                    ToastUtils.showToast(ActivityDialog.this, "删除位置为【" + postion + "】", ToastUtils.SUCCESS);
                    datas.remove(postion);
                    dialog.getShareBottomAdapter().notifyDataSetChanged();
                });
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
            case R.id.btn_popup_dialog:
//                SimpleCustomPop customPop = new SimpleCustomPop(this);
//                customPop.anchorView(btnPopupDialog);
//                customPop.show();
                View inflate = View.inflate(this, R.layout.popup_bubble_text, null);
                TextView tv = inflate.findViewById(R.id.tv_bubble);
                BubblePopup bubblePopup = new BubblePopup(this, inflate);
                tv.setText("最美的不是下雨天,是曾与你躲过雨的屋檐~");
                bubblePopup.anchorView(btnPopupDialog)
                        .gravity(Gravity.TOP)
                        .show();
                break;
            case R.id.btn_ed_dialog:
                MaterialNumDialog materialNumDialog = new MaterialNumDialog(this);
                materialNumDialog.title("自定义提示");
                materialNumDialog.setEtNum(0);
                materialNumDialog.setOnBtnClickL(
                        materialNumDialog::dismiss,
                        () -> {
                            ToastUtils.showToast(ActivityDialog.this, "输入未：" + materialNumDialog.getEtNum(), ToastUtils.SUCCESS);
                            materialNumDialog.dismiss();
                        }
                );
                materialNumDialog.show();
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
