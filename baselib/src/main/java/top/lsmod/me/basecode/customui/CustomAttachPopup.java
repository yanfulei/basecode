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
public class CustomAttachPopup extends HorizontalAttachPopupView {
    Ywsy ywsy;

    public CustomAttachPopup(@NonNull Context context, Ywsy ywsy) {
        super(context);
        this.ywsy = ywsy;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_b).setOnClickListener(v -> ywsy.onDongtan());
        findViewById(R.id.tv_d).setOnClickListener(v -> ywsy.onBiaoguan());
        findViewById(R.id.tv_j).setOnClickListener(v -> ywsy.onJingtan());
    }

    public interface Ywsy {
        void onDongtan();

        void onBiaoguan();

        void onJingtan();
    }
}
