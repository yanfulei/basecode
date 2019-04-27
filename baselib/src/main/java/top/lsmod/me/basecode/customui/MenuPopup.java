package top.lsmod.me.basecode.customui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.lxj.xpopup.core.AttachPopupView;
import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import net.cachapa.expandablelayout.ExpandableLayout;

import top.lsmod.me.basecode.R;


/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019/4/1 11:42 PM
 */
public class MenuPopup extends AttachPopupView {

    private TextView textView;
    private ExpandableLayout expandableLayout;

    public MenuPopup(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_menu_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
//        textView = findViewById(R.id.tv_test);
        expandableLayout = findViewById(R.id.expandable_layout);
//        textView.setOnClickListener(v -> {
//            if (expandableLayout.isExpanded()) {
//                expandableLayout.collapse();
//            } else {
//                expandableLayout.expand();
//            }
//        });
    }

    //完全可见执行
    @Override
    protected void onShow() {
        super.onShow();
    }

    //完全消失执行
    @Override
    protected void onDismiss() {

    }

    @Override
    protected int getMaxHeight() {
        return (int) (XPopupUtils.getWindowHeight(getContext()) * .85f);
    }
}