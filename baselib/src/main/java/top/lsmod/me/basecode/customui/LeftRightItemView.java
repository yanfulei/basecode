package top.lsmod.me.basecode.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
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
    // 间距
    private int lrvPadding;
    // 左边区域宽度
    private int lrvLeftWidth;
    // 左边颜色
    private int lrvLeftColor;
    // 右边颜色
    private int lrvRightColor;
    // 右边图标
    private int lrvRightIcon;

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
        lrvLeftWidth = array.getDimensionPixelSize(R.styleable.LeftRightItemView_lrv_left_width, 155);
        lrvLeftColor = array.getColor(R.styleable.LeftRightItemView_lrv_left_color, Color.parseColor("#8A000000"));
        lrvRightColor = array.getColor(R.styleable.LeftRightItemView_lrv_right_color, Color.parseColor("#262626"));
        lrvRightIcon = array.getResourceId(R.styleable.LeftRightItemView_lrv_right_icon, 0);
        array.recycle();
        tvLeftText.setText(leftText);
        tvRightText.setText(rightText);
        // 设置间距
        llAllView2.setPadding(lrvPadding, lrvPadding, lrvPadding, lrvPadding);
        // 设置左侧宽度
        ViewGroup.LayoutParams params = tvLeftText.getLayoutParams();
        params.width = lrvLeftWidth == 0 ? 150 : lrvLeftWidth;
        tvLeftText.setLayoutParams(params);
        // 设置颜色
        tvLeftText.setTextColor(lrvLeftColor);
        tvRightText.setTextColor(lrvRightColor);
        // 设置右侧图标
        tvRightText.setCompoundDrawablesWithIntrinsicBounds(null, null, (0 == lrvRightIcon ? null : getResources().getDrawable(lrvRightIcon)), null);
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

    public void setRightTextColor(int color) {
        tvRightText.setTextColor(color);
    }

    public void setLeftTextColor(int color) {
        tvLeftText.setTextColor(color);
    }
}
