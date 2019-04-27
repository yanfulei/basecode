package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import top.lsmod.me.basecode.BuildConfig;
import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.base.BaseInterface;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.eventbus.bean.TestInterfaceDemoBean;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Author:yanfulei
 * Date:2018/11/21
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityNetWork extends BaseActivityTitle {
    @BindView(R.id.btn_bat)
    Button btnBat;

    @Override
    public String setStatusBarColor() {
        return "#3F51B5";
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_one, null);
    }

    @Override
    public String setTitleBarText() {
        return "网络请求";
    }

    @Override
    public View customRightView() {
        View view = getLayoutInflater().inflate(R.layout.title_bar_left_view_menu1, null);
        view.findViewById(R.id.ibtn_menu).setOnClickListener(v -> ToastUtils.showToast(this, "bbbbbbbbbbbbbbb", ToastUtils.INFO));
        return view;
    }

    @OnClick({R.id.btn_bat})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bat:
                TestInterfaceDemoBean testInterfaceDemoBean = new TestInterfaceDemoBean();
                testInterfaceDemoBean.setCode("utf-8");
                testInterfaceDemoBean.setQ("女性");
                sendNetWorkRequest(testInterfaceDemoBean, BuildConfig.DEMO_INTERFACE, BaseInterface.TaoBaoDemo, true);
                break;
        }
    }

    @Override
    public void onNetWorkResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
        super.onNetWorkResponse(baseNetWorkEbRspBean);
        if (baseNetWorkEbRspBean.getInterfaceId() == (int) BaseInterface.TaoBaoDemo[1]) {
            ToastUtils.showToast(this, baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.SUCCESS);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
