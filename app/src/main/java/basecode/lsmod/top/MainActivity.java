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
    @BindView(R.id.btn_swich)
    Button btnSwich;
    @BindView(R.id.btn_search)
    Button btnSearch;

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

    @OnClick({R.id.btn_bat, R.id.btn_dialog, R.id.btn_swich, R.id.btn_search, R.id.btn_jwd, R.id.btn_gjt, R.id.btn_left_right_view, R.id.btn_time})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bat:
                startActivity(new Intent(this, ActivityNetWork.class));
                break;
            case R.id.btn_dialog:
                startActivity(new Intent(this, ActivityDialog.class));
                break;
            case R.id.btn_swich:
                startActivity(new Intent(this, ActivitySwitchButton.class));
                break;
            case R.id.btn_search:
                startActivity(new Intent(this, ActicitySearchTitle.class));
                break;
            case R.id.btn_jwd:
                startActivity(new Intent(this, ActivityGPRS.class));
                break;
            case R.id.btn_gjt:
                startActivity(new Intent(this, ActivityGjt.class));
                break;
            case R.id.btn_left_right_view:
                startActivity(new Intent(this, ActivityLeftRightView.class));
                break;
            case R.id.btn_time:
                startActivity(new Intent(this, ActivityTimeView.class));
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
