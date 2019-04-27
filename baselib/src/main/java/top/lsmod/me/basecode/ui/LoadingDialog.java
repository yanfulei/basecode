package top.lsmod.me.basecode.ui;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.base.BaseDialog;
import com.wang.avi.AVLoadingIndicatorView;

import top.lsmod.me.basecode.R;

/**
 * Created by yanfulei on 2018/10/6
 * Email yanfulei1990@gmail.com
 */
public class LoadingDialog extends BaseDialog<LoadingDialog> {
    private Context context;
    private AVLoadingIndicatorView avLoadingIndicatorView;
    private TextView tv_msg;
    private String msg; // 提示消息

    public LoadingDialog(Context context, String msg) {
        super(context);
        this.context = context;
        this.msg = msg;
    }

    //该方法用来出来数据初始化代码
    @Override
    public View onCreateView() {
        widthScale(0.25f);
        //填充弹窗布局
        View inflate = View.inflate(context, R.layout.dialog_loading, null);
        // 加载框
        avLoadingIndicatorView = inflate.findViewById(R.id.av_loading);
        // 提示消息
        tv_msg = inflate.findViewById(R.id.tv_msg);
        if (!TextUtils.isEmpty(msg)) {
            tv_msg.setText(msg);
        }
        return inflate;
    }

    //该方法用来处理逻辑代码
    @Override
    public void setUiBeforShow() {
        avLoadingIndicatorView.show();
    }
}
