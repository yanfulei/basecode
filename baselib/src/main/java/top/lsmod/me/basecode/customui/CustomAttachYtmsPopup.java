package top.lsmod.me.basecode.customui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.lxj.xpopup.core.HorizontalAttachPopupView;

import top.lsmod.me.basecode.R;

/**
 * Description:
 * Create by lxj, at 2019/3/13
 */
public class CustomAttachYtmsPopup extends HorizontalAttachPopupView {
    Ytms ytms;

    public CustomAttachYtmsPopup(@NonNull Context context, Ytms ytms) {
        super(context);
        this.ytms = ytms;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_ytms_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_hc).setOnClickListener(v -> {
            ytms.onHc();
        });
        findViewById(R.id.tv_ytms).setOnClickListener(v -> {
            ytms.onYtms();
        });
    }

    public interface Ytms {
        void onHc();

        void onYtms();
    }
}
