package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.flyco.dialog.widget.MaterialDialog;
import java.io.Serializable;
import top.lsmod.me.basecode.R;

/**
 * 崩溃弹框
 */
public class CrashDialogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carash_dialog);
        MaterialDialog customBaseDialog = new MaterialDialog(this);
        customBaseDialog.title("不小心崩溃了，错误日志正在上传，攻城狮晚饭钱要被扣了~~");
        customBaseDialog.show();
        ErrorBean errorBean = (ErrorBean) getIntent().getSerializableExtra("error_bean");
        // 上传至阿里云
//        AliLogUtils.error(errorBean.getStoreName(), errorBean.getSN(), "错误", errorBean.getErrorMsg(), errorBean.getALI_SLS_LOG_TYPE());
        customBaseDialog.setOnBtnClickL(
                () -> System.exit(0),
                () -> System.exit(0));
    }

    public static class ErrorBean implements Serializable {

        private String storeName;
        private String SN;
        private String errorMsg;
        private String ALI_SLS_LOG_TYPE;

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getSN() {
            return SN;
        }

        public void setSN(String SN) {
            this.SN = SN;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getALI_SLS_LOG_TYPE() {
            return ALI_SLS_LOG_TYPE;
        }

        public void setALI_SLS_LOG_TYPE(String ALI_SLS_LOG_TYPE) {
            this.ALI_SLS_LOG_TYPE = ALI_SLS_LOG_TYPE;
        }
    }
}
