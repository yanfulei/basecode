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
public class CustomAttachQSPopup extends HorizontalAttachPopupView {
    Qs qs;

    public CustomAttachQSPopup(@NonNull Context context, Qs qs) {
        super(context);
        this.qs = qs;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_qs_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_q).setOnClickListener(v -> qs.onQuyang());
        findViewById(R.id.tv_s).setOnClickListener(v -> qs.onShuiyang());
    }

    public interface Qs {
        void onQuyang();

        void onShuiyang();
    }
}
