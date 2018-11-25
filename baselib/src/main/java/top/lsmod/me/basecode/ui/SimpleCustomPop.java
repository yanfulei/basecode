package top.lsmod.me.basecode.ui;

import android.content.Context;
import android.view.View;

import com.flyco.dialog.widget.popup.base.BasePopup;

import top.lsmod.me.basecode.R;

/**
 * Author:yanfulei
 * Date:2018/11/25
 * Email:yanfulei1990@gmail.com
 **/
public class SimpleCustomPop extends BasePopup<SimpleCustomPop> {
    public SimpleCustomPop(Context context) {
        super(context);
    }

    @Override
    public View onCreatePopupView() {
        return View.inflate(mContext, R.layout.pupo_msg_view, null);
    }

    @Override
    public void setUiBeforShow() {

    }
}
