package basecode.lsmod.top;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityNoTitle;

/**
 * 测试
 */
public class MainActivity extends BaseActivityNoTitle {

    @BindView(R.id.btn_bat)
    Button btnBat;
    @BindView(R.id.btn_dialog)
    Button btnDialog;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_main, null);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.btn_bat, R.id.btn_dialog})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bat:
                startActivity(new Intent(this, ActivityNetWork.class));
                break;
            case R.id.btn_dialog:
                startActivity(new Intent(this, ActivityDialog.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void initSingle() {

    }
}
