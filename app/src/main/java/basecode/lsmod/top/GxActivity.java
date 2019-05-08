package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.utils.UpdateManager;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019-05-08 15:56
 */
public class GxActivity extends BaseActivityTitle {

    @BindView(R.id.btn_gx)
    Button btnGx;

    @OnClick({R.id.btn_gx})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_gx:
                UpdateManager.showUpdateDialog(this, "发现新版本", new UpdateManager.UpdateDialogCancelOkClickListener() {
                    @Override
                    public void onCancelClick() {

                    }

                    @Override
                    public void onOkClick() {

                    }
                });
                UpdateManager.setDownloadProgress(90);
                break;
        }
    }

    @Override
    public String setStatusBarColor() {
        return null;
    }

    @Override
    public View setContentView() {
        View view = getLayoutInflater().inflate(R.layout.activity_gx, null);
        return view;
    }

    @Override
    public String setTitleBarText() {
        return "更新弹框";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
