package top.lsmod.me.basecode.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 更新工具类
 */
public class UpdateManager {

    private final Activity mContext;// 上下文

    private String apkUrl; // apk下载地址
    private static final String savePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString(); // apk保存到SD卡的路径
    private static final String saveFileName = savePath + "/pda.apk"; // 完整路径名

    private ProgressBar mProgress; // 下载进度条控件
    private static final int DOWNLOADING = 1; // 表示正在下载
    private static final int DOWNLOADED = 2; // 下载完毕
    private static final int DOWNLOAD_FAILED = 3; // 下载失败
    private int progress; // 下载进度
    private boolean cancelFlag = false; // 取消下载标志位

    private String updateDescription; // 更新内容描述信息

    private AlertDialog alertDialog1, alertDialog2; // 表示提示对话框、进度条对话框
    private boolean isforce; // 是否为强制更新
    private IUpdateOp l;

    public UpdateManager(Activity context, String updateDescription1, String apkUrl, boolean isforce, IUpdateOp l) {
        this.mContext = context;
        this.apkUrl = apkUrl == null ? "" : apkUrl;
        updateDescription = updateDescription1 == null ? "" : updateDescription1;
        this.isforce = isforce;
        this.l = l;
    }

    /**
     * 显示更新对话框
     */
    public void showNoticeDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
        dialog.setTitle("发现新版本");
        dialog.setMessage(updateDescription);
        dialog.setPositiveButton("现在更新", (arg0, arg1) -> {
            arg0.dismiss();
            // 通过浏览器下载
            Uri uri = Uri.parse(this.apkUrl);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            mContext.startActivity(intent);
            // 本地下载方式
//            showDownloadDialog();
        });
        if (!isforce) {
            dialog.setNeutralButton("下次更新", (arg0, arg1) -> {
                arg0.dismiss();
                l.OpSuccess();
            });
        }
        alertDialog1 = dialog.create();
        alertDialog1.setCancelable(false);
        if (!alertDialog1.isShowing()) {
            alertDialog1.show();
        }
    }

    /**
     * 下载apk的线程
     */
    public void downloadAPK() {
        new Thread(() -> {
            try {
                URL url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                int length = conn.getContentLength();
                InputStream is = conn.getInputStream();

                File file = new File(savePath);
                if (!file.exists()) {
                    file.mkdir();
                }
                String apkFile = saveFileName;
                File ApkFile = new File(apkFile);
                FileOutputStream fos = new FileOutputStream(ApkFile);

                int count = 0;
                byte buf[] = new byte[1024];

                do {
                    int numread = is.read(buf);
                    count += numread;
                    progress = (int) (((double) count / length) * 100);
                    // 更新进度
                    mHandler.sendEmptyMessage(DOWNLOADING);
                    if (numread <= 0) {
                        // 下载完成通知安装
                        mHandler.sendEmptyMessage(DOWNLOADED);
                        break;
                    }
                    fos.write(buf, 0, numread);
                } while (!cancelFlag); // 点击取消就停止下载.

                fos.close();
                is.close();
            } catch (Exception e) {
                mHandler.sendEmptyMessage(DOWNLOAD_FAILED);
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 更新UI的handler
     */
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOADING:
                    mProgress.setProgress(progress);
                    break;
                case DOWNLOADED:
                    if (alertDialog2 != null && alertDialog2.isShowing()) {
                        alertDialog2.dismiss();
                    }
                    installAPK();
                    break;
                case DOWNLOAD_FAILED:
                    if (alertDialog2 != null && alertDialog2.isShowing()) {
                        alertDialog2.dismiss();
                    }
                    ToastUtils.showToast(mContext, "下载失败,请重试!", ToastUtils.ERROR);
                    l.OpFail();
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 下载完成后自动安装apk
     */
    public void installAPK() {
        File apkFile = new File(saveFileName);
        if (!apkFile.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkFile.toString()),
                "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

    public interface IUpdateOp {
        void OpSuccess();

        void OpFail();
    }
}
