package basecode.lsmod.top;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.widget.MsgView;

import java.util.ArrayList;

import basecode.lsmod.top.bean.TabEntity;
import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.me.basecode.base.BaseActivityTitle;

/**
 * Author:yanfulei
 * Date:2018/12/27
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityCommonTabLayout extends BaseActivityTitle {
    @BindView(R.id.tl_4)
    CommonTabLayout ctlTab;

    private String[] mTitles = {"首页", "消息", "联系人", "更多"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private int[] mIconSelectIds = {
            R.mipmap.ic_launcher, R.mipmap.ic_launcher,
            R.mipmap.ic_launcher, R.mipmap.ic_launcher};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public String setStatusBarColor() {
        return "#3F51B5";
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_common_tablayout, null);
    }

    @Override
    public String setTitleBarText() {
        return "CommonTabLayout";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        ctlTab.setTabData(mTabEntities);
        //设置未读消息背景
        ctlTab.showMsg(3, 5);
        ctlTab.setMsgMargin(3, 0, 5);
        MsgView rtv_2_31 = ctlTab.getMsgView(3);
        if (rtv_2_31 != null) {
            rtv_2_31.setBackgroundColor(Color.parseColor("#6D8FB0"));
        }
        ctlTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }
}
