package basecode.lsmod.top;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.othershe.combinebitmap.CombineBitmap;
import com.othershe.combinebitmap.layout.WechatLayoutManager;
import com.othershe.combinebitmap.listener.OnSubItemClickListener;

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
    @BindView(R.id.iv11)
    ImageView iv11;

    private String[] IMG_URL_ARR = {
            "http://img.hb.aicdn.com/eca438704a81dd1fa83347cb8ec1a49ec16d2802c846-laesx2_fw658",
            "http://img.hb.aicdn.com/729970b85e6f56b0d029dcc30be04b484e6cf82d18df2-XwtPUZ_fw658",
            "http://img.hb.aicdn.com/85579fa12b182a3abee62bd3fceae0047767857fe6d4-99Wtzp_fw658",
            "http://img.hb.aicdn.com/2814e43d98ed41e8b3393b0ff8f08f98398d1f6e28a9b-xfGDIC_fw658",
            "http://img.hb.aicdn.com/a1f189d4a420ef1927317ebfacc2ae055ff9f212148fb-iEyFWS_fw658",
            "http://img.hb.aicdn.com/69b52afdca0ae780ee44c6f14a371eee68ece4ec8a8ce-4vaO0k_fw658",
            "http://img.hb.aicdn.com/9925b5f679964d769c91ad407e46a4ae9d47be8155e9a-seH7yY_fw658",
            "http://img.hb.aicdn.com/e22ee5730f152c236c69e2242b9d9114852be2bd8629-EKEnFD_fw658",
            "http://img.hb.aicdn.com/73f2fbeb01cd3fcb2b4dccbbb7973aa1a82c420b21079-5yj6fx_fw658",
    };

    private void loadWechatBitmap(ImageView imageView, int count) {
        CombineBitmap.init(this)
                .setLayoutManager(new WechatLayoutManager())
                .setSize(180)
                .setGap(3)
                .setGapColor(Color.parseColor("#E8E8E8"))
                .setUrls(getUrls(count))
                .setImageView(imageView)
                .setOnSubItemClickListener(index -> Log.e("SubItemIndex", "--->" + index))
                .build();
    }

    private String[] getUrls(int count) {
        String[] urls = new String[count];
        System.arraycopy(IMG_URL_ARR, 0, urls, 0, count);
        return urls;
    }

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
        loadWechatBitmap(iv11, 9);
    }
}
