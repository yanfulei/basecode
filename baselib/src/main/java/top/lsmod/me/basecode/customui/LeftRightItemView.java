package top.lsmod.me.basecode.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import top.lsmod.me.basecode.R;

/**
 * Author:yanfulei
 * Date:2018/12/3
 * Email:yanfulei1990@gmail.com
 **/
public class LeftRightItemView extends LinearLayout {

    public TextView tvLeftText;
    public TextView tvRightText;
    public String leftText;
    public String rightText;
    public LinearLayout llAllView;
    public LinearLayout llAllView2;
    private int lrvPadding;

    public LeftRightItemView(Context context) {
        this(context, null);
    }

    public LeftRightItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.left_tight_item_view, this);
        tvLeftText = findViewById(R.id.tv_left_title);
        tvRightText = findViewById(R.id.tv_right_text);
        llAllView = findViewById(R.id.ll_allview);
        llAllView2 = findViewById(R.id.ll_allview2);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LeftRightItemView);
        leftText = array.getString(R.styleable.LeftRightItemView_left_text);
        rightText = array.getString(R.styleable.LeftRightItemView_right_text);
        lrvPadding = array.getDimensionPixelSize(R.styleable.LeftRightItemView_lrv_padding, 10);
        array.recycle();
        tvLeftText.setText(leftText);
        tvRightText.setText(rightText);
        // 设置间距
        llAllView2.setPadding(lrvPadding, lrvPadding, lrvPadding, lrvPadding);
    }

    public void setOnClickListener(OnClickListener v) {
        llAllView.setOnClickListener(v);
    }

    public void setLeftText(String text) {
        tvLeftText.setText(text);
    }

    public void setRightText(String rightText) {
        tvRightText.setText(rightText);
    }
}
