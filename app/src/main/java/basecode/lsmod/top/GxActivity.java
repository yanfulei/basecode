package basecode.lsmod.top;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.base.BaseOkHttp;
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
    private int test;

    @OnClick({R.id.btn_gx})
    void Onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_gx:
                UpdateManager.showUpdateDialog(this, "发现新版本", new UpdateManager.UpdateDialogCancelOkClickListener() {
                    @Override
                    public void onCancelClick() {
                        String test = "/storage/emulated/0/basecode.lsmod.top/_temp.apk";
                        runOnUiThread(() -> UpdateManager.installAPK(GxActivity.this, test));
                    }

                    @Override
                    public void onOkClick() {
                        String fileFolderPath = Environment.getExternalStorageDirectory() + File.separator + GxActivity.this.getPackageName() + File.separator;
                        BaseOkHttp.AsyncDownload("https://zhkc.oss-cn-hangzhou.aliyuncs.com/ican.1.0.0.apk", fileFolderPath, "_temp.apk", new BaseOkHttp.DownloadMonitor() {
                            @Override
                            public void onError(String error) {
                            }

                            @Override
                            public void onProgress(int progress) {
                                if (progress > test) {
                                    runOnUiThread(() -> UpdateManager.setDownloadProgress(test, progress));
                                    Log.d(TAG, "onProgress: ----------------" + progress);
                                    test = progress;
                                }
                            }

                            @Override
                            public void onDownloadSuccess(String filePath) {
                                runOnUiThread(() -> UpdateManager.installAPK(GxActivity.this, filePath));
                                test = 0;
                            }

                            @Override
                            public void onDownloadFailed() {

                            }
                        });
                    }
                });
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
