package top.lsmod.me.basecode.customui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lxj.xpopup.core.BottomPopupView;
import com.lxj.xpopup.util.XPopupUtils;

import top.lsmod.me.basecode.R;


/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019/4/1 11:42 PM
 */
public class ZhihuCommentPopup extends BottomPopupView {

    WebView webView;
    String url;

    public ZhihuCommentPopup(@NonNull Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.custom_bottom_popup;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        webView = findViewById(R.id.wv_baike);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://baike.baidu.com/item/" + this.url);
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