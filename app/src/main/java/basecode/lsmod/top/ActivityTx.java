package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;

import java.util.ArrayList;
import java.util.List;

import basecode.lsmod.top.adapter.AdapterTx;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;

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
        for (int i=0;i<10;i++){
            View view = this.getLayoutInflater().inflate(R.layout.activity_main, null);
            Button button = view.findViewById(R.id.btn_bat);
            button.setText("第"+i+"个元素");
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
    }
}
