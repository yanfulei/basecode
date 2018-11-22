package basecode.lsmod.top;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    public void initSingle() {

    }

    @Override
    public int setStatusBarColor() {
        return 0;
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
    public void initData() {

    }

    @OnClick({R.id.btn_bat})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.btn_bat:
                TestInterfaceDemoBean testInterfaceDemoBean = new TestInterfaceDemoBean();
                testInterfaceDemoBean.setCode("utf-8");
                testInterfaceDemoBean.setQ("女性");
                sendNetWorkRequest(testInterfaceDemoBean, BuildConfig.DEMO_INTERFACE, BaseInterface.TaoBaoDemo);
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
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }
}
