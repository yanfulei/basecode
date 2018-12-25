package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import top.lsmod.me.basecode.BuildConfig;
import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.eventbus.bean.TestInterfaceDemoBean;
import top.lsmod.me.basecode.ui.LoadingDialog;
import top.lsmod.me.basecode.utils.HttpUtils;
import top.lsmod.me.basecode.utils.StatusBarUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Created by yanfulei on 2018/10/4
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseActivityTitle extends Activity {

    public CommonTitleBar commonTitleBar;
    // 加载框
    private LoadingDialog adDialog;
    // 内容区域
    private LinearLayout llContent;
    // 所有布局
    private LinearLayout llAllView;
    // 是否已经注册EventBus
    public boolean isBaseRegistered;
    // 列表没有数据展示
    private TextView tvNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_activity_title);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = setContentView();
        llContent = findViewById(R.id.ll_content);
        llAllView = findViewById(R.id.ll_allview);
        tvNoData = findViewById(R.id.tv_no_data);
        llContent.addView(view, params);
        // 设置导航栏颜色
        StatusBarUtils.setWindowStatusBarColor(this, setStatusBarColor() == 0 ? R.color.white : setStatusBarColor());
        initParentView();
    }

    private void initParentView() {
        commonTitleBar = findViewById(R.id.titlebar);
        commonTitleBar.getCenterTextView().setText(setTitleBarText());
        commonTitleBar.getCenterTextView().setTextColor(Color.parseColor("#ffffff"));
        commonTitleBar.getLeftImageButton().setOnClickListener(view -> finish());
        commonTitleBar.setSearchRightImageResource(R.drawable.ic_more_horiz_black_24dp);
        commonTitleBar.getRightImageButton().setOnClickListener(view -> onRightImageButtonClick());
        // 设置右侧操作按钮是否展示
        commonTitleBar.getRightImageButton().setVisibility(showRightTitleButton() ? View.VISIBLE : View.GONE);
    }

    /**
     * 展示列表暂无数据
     */
    public void showNoDataList() {
        tvNoData.setVisibility(View.VISIBLE);
    }

    /**
     * 获取所有布局
     */
    public LinearLayout getLlAllView() {
        return llAllView;
    }

    /**
     * 右侧按钮点击
     */
    public void onRightImageButtonClick() {

    }

    /**
     * 设置状态栏颜色
     *
     * @return
     */
    public abstract int setStatusBarColor();

    /**
     * 获取子布局
     */
    public abstract View setContentView();

    /**
     * 设置标题
     */
    public abstract String setTitleBarText();

    /**
     * 是否展示右侧操作按钮
     */
    public abstract boolean showRightTitleButton();

    /**
     * 展示加载框
     */
    public void showLoading() {
        if (null != adDialog){
            adDialog.dismiss();
        }
        adDialog = new LoadingDialog(this);
        adDialog.onCreateView();
        adDialog.setUiBeforShow();
        //点击空白区域能不能退出
        adDialog.setCanceledOnTouchOutside(false);
        //按返回键能不能退出
        adDialog.setCancelable(true);
        adDialog.dimEnabled(false);
        adDialog.show();
    }

    /**
     * 关闭弹框
     */
    public void hideLoading() {
        if (adDialog.isShowing()) {
            adDialog.dismiss();
        }
    }

    /**
     * 发送网络请求demo
     */
    public void sendNetWorkDemo() {
        TestInterfaceDemoBean testInterfaceDemoBean = new TestInterfaceDemoBean();
        testInterfaceDemoBean.setCode("utf-8");
        testInterfaceDemoBean.setQ("内衣");
        sendNetWorkRequest(testInterfaceDemoBean, BuildConfig.DEMO_INTERFACE, BaseInterface.TaoBaoDemo);
    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequestAuto(BaseReqBean bean) {
        if (!isBaseRegistered && !EventBus.getDefault().isRegistered(this)) {
            // EventBus注册
            EventBus.getDefault().register(this);
            isBaseRegistered = true;
        }
        // OKHTTP注册
        new BaseOkHttp().initNetWorkPlugin(this);
        BaseNetWorkEbReqBean baseNetWorkEbReqBean = new BaseNetWorkEbReqBean();
        Gson gson = new Gson();
        String json = gson.toJson(bean);
        Log.d("sendNetWorkRequest", json);
        baseNetWorkEbReqBean.setJson(json);
        // 是否为错误自动弹出
        baseNetWorkEbReqBean.setAuto(true);
        // 发送网络请求
        EventBus.getDefault().post(baseNetWorkEbReqBean);
    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(Object bean, String serverLocal, Object[] interfaceInfo) {
        showLoading();
        if (!isBaseRegistered && !EventBus.getDefault().isRegistered(this)) {
            // EventBus注册
            EventBus.getDefault().register(this);
            isBaseRegistered = true;
        }
        // OKHTTP注册
        new BaseOkHttp().initNetWorkPlugin(this);
        BaseNetWorkEbReqBean baseNetWorkEbReqBean = new BaseNetWorkEbReqBean();
        // 设置上下文
        baseNetWorkEbReqBean.setActivity(this);
        // url请求参数
        if (interfaceInfo[2].equals("get") || interfaceInfo[2].equals("delete")) {
            String param = "";
            try {
                param = HttpUtils.parseURLPair(bean);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 请求路径
            baseNetWorkEbReqBean.setUrl(serverLocal + interfaceInfo[0] + "?" + param);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson(bean);
            Log.d("sendNetWorkRequest", json);
            // json请求参数
            baseNetWorkEbReqBean.setJson(json);
            // 请求路径
            baseNetWorkEbReqBean.setUrl(serverLocal + interfaceInfo[0]);
        }
        // 是否为错误自动弹出
        baseNetWorkEbReqBean.setAuto(true);
        // 请求类型
        baseNetWorkEbReqBean.setHttpType((String) interfaceInfo[2]);
        // 请求ID
        baseNetWorkEbReqBean.setInterfaceId((Integer) interfaceInfo[1]);
        // 发送网络请求
        EventBus.getDefault().post(baseNetWorkEbReqBean);
    }

    /**
     * 接口请求数据返回
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onOkHttpResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
        // 隐藏列表无数据布局
        tvNoData.setVisibility(View.GONE);
        // 隐藏弹出框
        hideLoading();
        // 错误自动弹出
//        if (baseNetWorkEbRspBean.isAuto()) {
//            if (!baseNetWorkEbRspBean.isSuccess()) {
//                ToastUtils.showToast(this, baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
//                return;
//            }
//            // 业务层是否错误
//            Gson gson = new Gson();
//            BaseRspBean baseRspBean = gson.fromJson(baseNetWorkEbRspBean.getHttpMsg(), BaseRspBean.class);
//            if (!baseRspBean.Success) {
//                ToastUtils.showToast(this, baseRspBean.getMessage(), ToastUtils.ERROR);
//                return;
//            }
//        }
        onNetWorkResponse(baseNetWorkEbRspBean);
    }

    /**
     * 初步处理后返回activity
     */
    public void onNetWorkResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
