package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;
import top.lsmod.me.basecode.base.BaseActivityTitle;

public class FormActivity extends BaseActivityTitle {
    @BindView(R.id.extended_edit_text)
    ExtendedEditText extendedEditText;
    @BindView(R.id.text_field_boxes)
    TextFieldBoxes textFieldBoxes;
    @BindView(R.id.extended_edit_text1)
    ExtendedEditText extendedEditText1;
    @BindView(R.id.text_field_boxes1)
    TextFieldBoxes textFieldBoxes1;
    @BindView(R.id.extended_edit_text2)
    ExtendedEditText extendedEditText2;
    @BindView(R.id.text_field_boxes2)
    TextFieldBoxes textFieldBoxes2;
    @BindView(R.id.extended_edit_text3)
    ExtendedEditText extendedEditText3;
    @BindView(R.id.text_field_boxes3)
    TextFieldBoxes textFieldBoxes3;
    @BindView(R.id.extended_edit_text4)
    ExtendedEditText extendedEditText4;
    @BindView(R.id.text_field_boxes4)
    TextFieldBoxes textFieldBoxes4;
    @BindView(R.id.extended_edit_text5)
    ExtendedEditText extendedEditText5;
    @BindView(R.id.text_field_boxes5)
    TextFieldBoxes textFieldBoxes5;
    @BindView(R.id.extended_edit_text6)
    ExtendedEditText extendedEditText6;
    @BindView(R.id.text_field_boxes6)
    TextFieldBoxes textFieldBoxes6;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_form, null);
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        extendedEditText3.setError("我是代码设置的错误信息");
    }
}
