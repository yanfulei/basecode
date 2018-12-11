package top.lsmod.me.basecode.customui.dialog;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.flyco.dialog.utils.CornerUtils;
import com.flyco.dialog.widget.MaterialDialog;
import com.flyco.dialog.widget.internal.BaseAlertDialog;

import top.lsmod.me.basecode.R;

/**
 * Author:yanfulei
 * Date:2018/12/7
 * Email:yanfulei1990@gmail.com
 **/
public class MaterialConstomUiDialog extends BaseAlertDialog<MaterialDialog> {
    private View view;

    public MaterialConstomUiDialog(Context context, View view) {
        super(context);
        this.view = view;
        /** default value*/
        mTitleTextColor = Color.parseColor("#DE000000");
        mTitleTextSize = 22f;
        mContentTextColor = Color.parseColor("#8a000000");
        mContentTextSize = 16f;
        mLeftBtnTextColor = Color.parseColor("#383838");
        mRightBtnTextColor = Color.parseColor("#468ED0");
        mMiddleBtnTextColor = Color.parseColor("#00796B");
        /** default value*/
    }

    @Override
    public View onCreateView() {

        /** title */
        mTvTitle.setGravity(Gravity.CENTER_VERTICAL);
        mTvTitle.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(0));
        mTvTitle.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLlContainer.addView(mTvTitle);

        /** content */
//        mTvContent.setPadding(dp2px(20), dp2px(20), dp2px(20), dp2px(20));
//        mTvContent.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT));
        mLlContainer.addView(view);

        /**btns*/
        mLlBtns.setGravity(Gravity.RIGHT);
        mLlBtns.addView(mTvBtnLeft);
        mLlBtns.addView(mTvBtnMiddle);
        mLlBtns.addView(mTvBtnRight);

        mTvBtnLeft.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        mTvBtnRight.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        mTvBtnMiddle.setPadding(dp2px(15), dp2px(8), dp2px(15), dp2px(8));
        mLlBtns.setPadding(dp2px(20), dp2px(0), dp2px(10), dp2px(10));

        mLlContainer.addView(mLlBtns);

        return mLlContainer;
    }

    @Override
    public void setUiBeforShow() {
        super.setUiBeforShow();
        /**set background color and corner radius */
        float radius = dp2px(mCornerRadius);
        mLlContainer.setBackgroundDrawable(CornerUtils.cornerDrawable(mBgColor, radius));
        mTvBtnLeft.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
        mTvBtnRight.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
        mTvBtnMiddle.setBackgroundDrawable(CornerUtils.btnSelector(radius, mBgColor, mBtnPressColor, -2));
    }
}
