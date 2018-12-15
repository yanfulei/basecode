package top.lsmod.me.basecode.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import top.lsmod.me.basecode.R;

/**
 * Author:yanfulei
 * Date:2018/11/3
 * Email:yanfulei1990@gmail.com
 **/
public class ScannerItemView extends LinearLayout {

    public EditText et_input;
    public LinearLayout iv_right_icon;

    public ScannerItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 加载布局
        LayoutInflater.from(context).inflate(R.layout.custom_scanner_item_view, this);
        et_input = findViewById(R.id.et_input);
        iv_right_icon = findViewById(R.id.iv_right_icon);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ScannerItemView);
        // 左侧内容
        String text = typedArray.getText
                (R.styleable.ScannerItemView_sciv_left_text).toString();
        et_input.setHint(text);
    }

    public void setOnclickRight(OnClickListener listener) {
        iv_right_icon.setOnClickListener(listener);
    }

    public void setEt_input_Text(String text) {
        et_input.setHint(text);
    }

    public void setEtEnterListener(TextView.OnEditorActionListener listener) {
        this.et_input.setOnEditorActionListener(listener);
    }
}
