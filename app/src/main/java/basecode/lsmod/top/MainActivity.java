package basecode.lsmod.top;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;
import top.lsmod.me.basecode.base.BaseActivityNoTitle;
import top.lsmod.me.basecode.utils.PermissionsLogUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * 测试
 */
public class MainActivity extends BaseActivityNoTitle implements EasyPermissions.PermissionCallbacks {

    @BindView(R.id.btn_bat)
    Button btnBat;
    @BindView(R.id.btn_dialog)
    Button btnDialog;
    @BindView(R.id.btn_swich)
    Button btnSwich;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.btn_jwd)
    Button btnJwd;
    @BindView(R.id.btn_gjt)
    Button btnGjt;
    @BindView(R.id.btn_left_right_view)
    Button btnLeftRightView;
    @BindView(R.id.btn_time)
    Button btnTime;
    @BindView(R.id.btn_del)
    Button btnDel;
    @BindView(R.id.btn_ui)
    Button btnUi;
    @BindView(R.id.btn_bengkui)
    Button btnBengkui;
    @BindView(R.id.btn_shoufengqin)
    Button btnShoufengqin;
    @BindView(R.id.btn_CommonTabLayout)
    Button btnCommonTabLayout;
    @BindView(R.id.btn_form)
    Button btnForm;
    @BindView(R.id.btn_tx)
    Button btnTx;
    @BindView(R.id.btn_xlsx_zhsc)
    Button btnXlsxZhsc;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_main, null);
    }

    @OnClick({R.id.btn_bat, R.id.btn_dialog, R.id.btn_swich, R.id.btn_search, R.id.btn_jwd, R.id.btn_gjt,
            R.id.btn_left_right_view, R.id.btn_time, R.id.btn_del, R.id.btn_ui, R.id.btn_bengkui, R.id.btn_shoufengqin,
            R.id.btn_CommonTabLayout, R.id.btn_form, R.id.btn_tx, R.id.btn_xlsx_zhsc, R.id.btn_qltx})
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
                startActivity(new Intent(this, ActivityCustomView.class));
                break;
            case R.id.btn_time:
                startActivity(new Intent(this, ActivityTimeView.class));
                break;
            case R.id.btn_del:
                startActivity(new Intent(this, DelActivity.class));
                break;
            case R.id.btn_ui:
                startActivity(new Intent(this, ActivityUi.class));
                break;
            case R.id.btn_bengkui:
                startActivity(new Intent(this, BengKuiActivity.class));
                break;
            case R.id.btn_shoufengqin:
                startActivity(new Intent(this, ActivityShouFengQin.class));
                break;
            case R.id.btn_CommonTabLayout:
                startActivity(new Intent(this, ActivityCommonTabLayout.class));
                break;
            case R.id.btn_form:
                startActivity(new Intent(this, FormActivity.class));
                break;
            case R.id.btn_tx:
                startActivity(new Intent(this, ActivityTx.class));
                break;
            case R.id.btn_xlsx_zhsc:
                startActivity(new Intent(this, ActivityXlsxZhsc.class));
                break;
            case R.id.btn_qltx:
                startActivity(new Intent(this, FormActivity.class));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        String str = PermissionsLogUtils.easyCheckPermissions(this,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        ToastUtils.showToast(this, str, ToastUtils.INFO);

        EasyPermissions.requestPermissions(this, "接下来需要获取WRITE_EXTERNAL_STORAGE权限", 0,
                Manifest.permission.ACCESS_FINE_LOCATION);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        ToastUtils.showToast(this, "获取权限成功", ToastUtils.INFO);
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

        ToastUtils.showToast(this, "获取权限失败", ToastUtils.INFO);
    }
}
