package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.gigamole.infinitecycleviewpager.OnInfiniteCyclePageTransformListener;

import java.util.ArrayList;
import java.util.List;

import basecode.lsmod.top.adapter.AdapterTx;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.BlurTransformation;
import top.lsmod.me.basecode.base.BaseActivityTitle;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description:
 * @date: 2019/3/8 11:39 PM
 */
public class ActivityTx extends BaseActivityTitle {
    @BindView(R.id.icvp_list)
    HorizontalInfiniteCycleViewPager icvpList;

    List<View> views;
    List<String> datas;
    AdapterTx adapterTx;
    @BindView(R.id.ll_all_view)
    LinearLayout llAllView;
    @BindView(R.id.iv_images)
    ImageView ivImages;

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_tx, null);
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
        initData();
    }

    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("第" + i + "项");
        }
        views = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            View view = this.getLayoutInflater().inflate(R.layout.activity_main, null);
            Button button = view.findViewById(R.id.btn_bat);
            button.setText("第" + i + "个元素");
            views.add(view);
        }
        adapterTx = new AdapterTx(this, views, datas);
        icvpList.setAdapter(adapterTx);
        icvpList.setScrollDuration(500);
//        icvpList.setInterpolator(...);
        icvpList.setMediumScaled(true);
        icvpList.setMaxPageScale(0.8F);
        icvpList.setMinPageScale(0.5F);
        icvpList.setCenterPageScaleOffset(30.0F);
        icvpList.setMinPageScaleOffset(5.0F);
//        icvpList.setOnInfiniteCyclePageTransformListener(...);
        icvpList.setOnInfiniteCyclePageTransformListener(new OnInfiniteCyclePageTransformListener() {
            @Override
            public void onPreTransform(View page, float position) {

            }

            @Override
            public void onPostTransform(View page, float position) {
                Glide.with(ActivityTx.this).load(R.drawable.ic_launcher_background)
                        .apply(bitmapTransform(new BlurTransformation(25)))
                        .into(ivImages);
            }
        });
    }
}
