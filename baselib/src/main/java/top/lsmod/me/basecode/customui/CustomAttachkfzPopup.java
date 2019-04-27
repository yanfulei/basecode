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
public class CustomAttachkfzPopup extends HorizontalAttachPopupView {
    Kfz kfz;

    public CustomAttachkfzPopup(@NonNull Context context, Kfz kfz) {
        super(context);
        this.kfz = kfz;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_attach_kfz_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        findViewById(R.id.tv_k).setOnClickListener(v -> kfz.onKaiKong());
        findViewById(R.id.tv_f).setOnClickListener(v -> kfz.onFengKong());
        findViewById(R.id.tv_z).setOnClickListener(v -> kfz.onZhongKong());
    }

    public interface Kfz {
        void onKaiKong();

        void onFengKong();

        void onZhongKong();
    }
}
