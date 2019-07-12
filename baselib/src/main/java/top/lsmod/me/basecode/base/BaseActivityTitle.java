package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.wuhenzhizao.titlebar.widget.CommonTitleBar;

import top.lsmod.me.basecode.BuildConfig;
import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.eventbus.bean.TestInterfaceDemoBean;
import top.lsmod.me.basecode.receiver.NetWorkChangReceiver;
import top.lsmod.me.basecode.ui.LoadingDialog;
import top.lsmod.me.basecode.utils.HttpUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Created by yanfulei on 2018/10/4
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseActivityTitle extends Activity {
    public String TAG = "YanFulei";
    public CommonTitleBar commonTitleBar;
    // 加载框
    private LoadingDialog adDialog;
    // 内容区域
    private LinearLayout llContent;
    // 所有布局
    private View llAllView;
    // 列表没有数据展示
    private TextView tvNoData;
    // 网络状态
    private boolean isRegistered = false;
    private NetWorkChangReceiver netWorkChangReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 是否全屏
        if (isFullScreen()) {
            setContentView(R.layout.activity_base_activity_title_full);
        } else {
            setContentView(R.layout.activity_base_activity_title);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = setContentView();
        llContent = findViewById(R.id.ll_content);
        llAllView = findViewById(R.id.ll_allview);
        tvNoData = findViewById(R.id.tv_no_data);
        if (null != view) {
            llContent.addView(view, params);
        }
        initParentView();
        // 注册网络管理器
        initNetStataManger();
    }

    private void initParentView() {
        commonTitleBar = findViewById(R.id.titlebar);
        commonTitleBar.getCenterTextView().setTextColor(Color.parseColor("#ffffff"));
        commonTitleBar.setSearchRightImageResource(R.drawable.ic_more_horiz_black_24dp);
        // 设置导航栏颜色
        commonTitleBar.setBackgroundColor(TextUtils.isEmpty(setStatusBarColor()) ? Color.parseColor("#EA1C27") : Color.parseColor(setStatusBarColor()));
        // 定义titlebar右侧布局
        LinearLayout right = (LinearLayout) commonTitleBar.getRightCustomView();
        right.addView(null == customRightView() ? new View(this) : customRightView());
        // 传递titlebar对象
        giveCommonTitleBarObj(commonTitleBar);
        // 设置左侧布局
        LinearLayout left = (LinearLayout) commonTitleBar.getLeftCustomView();
        if (null == customLeftView()) {
            View view = getLayoutInflater().inflate(R.layout.title_bar_left_nomarl, null);
            view.findViewById(R.id.ll_all).setOnClickListener(v -> finish());
            left.addView(view);
        } else {
            left.addView(customLeftView());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        commonTitleBar.getCenterTextView().setText(setTitleBarText());
    }

    /**
     * 网络状态监听
     */
    protected void initNetStataManger() {
        //注册网络状态监听广播
        netWorkChangReceiver = new NetWorkChangReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkChangReceiver, filter);
        isRegistered = true;
    }

    /**
     * 展示列表暂无数据
     */
    public void showNoDataList() {
        tvNoData.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏列表暂无数据
     */
    public void hideNoDataList() {
        tvNoData.setVisibility(View.GONE);
    }

    /**
     * 获取所有布局
     */
    public View getLlAllView() {
        return llAllView;
    }

    /**
     * 自定义右侧布局
     */
    public View customRightView() {
        return null;
    }

    /**
     * 自定义左侧布局
     */
    public View customLeftView() {
        return null;
    }

    /**
     * 设置状态栏颜色
     *
     * @return
     */
    public abstract String setStatusBarColor();

    /**
     * 获取子布局
     */
    public abstract View setContentView();

    /**
     * 设置标题
     */
    public abstract String setTitleBarText();

    /**
     * 传递CommonTitleBar对象
     *
     * @param commonTitleBar
     */
    public void giveCommonTitleBarObj(CommonTitleBar commonTitleBar) {

    }

    /**
     * 是否为全屏模式
     *
     * @return
     */
    public boolean isFullScreen() {
        return false;
    }

    /**
     * 展示加载框
     */
    public void showLoading(String msg, boolean canceled) {
        try {
            if (null != adDialog) {
                adDialog.dismiss();
            }
            adDialog = new LoadingDialog(this, msg);
            adDialog.onCreateView();
            adDialog.setUiBeforShow();
            //点击空白区域能不能退出
            adDialog.setCanceledOnTouchOutside(false);
            //按返回键能不能退出
            adDialog.setCancelable(true);
            adDialog.show();
        } catch (Exception e) {
            Log.e(TAG, "showLoading: 出现弹出框无附着点错误", e);
        }
    }

    /**
     * 关闭弹框
     */
    public void hideLoading() {
        if (null != adDialog && adDialog.isShowing()) {
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
        sendNetWorkRequest(testInterfaceDemoBean, BuildConfig.DEMO_INTERFACE, BaseInterface.TaoBaoDemo, true);
    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(Object bean, String serverLocal, Object[] interfaceInfo, boolean isShowLoading) {
        if (isShowLoading) showLoading("正在加载", true);
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
        // 请求类型
        baseNetWorkEbReqBean.setHttpType((String) interfaceInfo[2]);
        // 请求ID
        baseNetWorkEbReqBean.setInterfaceId((Integer) interfaceInfo[1]);
        // 上下文
        baseNetWorkEbReqBean.setContext(getApplicationContext());
        // 发送网络请求
        BaseOkHttp.onNetWorkFetch(baseNetWorkEbReqBean, new BaseOkHttp.NetWorkMonitor() {
            @Override
            public void onSuccess(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                tvNoData.setVisibility(View.GONE); // 隐藏列表无数据布局
                hideLoading(); // 隐藏弹出框
                onNetWorkResponse(baseNetWorkEbRspBean); // 传递对象
            }

            @Override
            public void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                tvNoData.setVisibility(View.GONE); // 隐藏列表无数据布局
                hideLoading(); // 隐藏弹出框
                ToastUtils.showSnackbar(BaseActivityTitle.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
                onNetWorkResponse(baseNetWorkEbRspBean);
            }
        });
    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(Object bean, String serverLocal, Object[] interfaceInfo, boolean isShowLoading,
                                   BaseOkHttp.RealTimeNetWorkMonitor callback) {
        if (isShowLoading) showLoading("正在加载", true);
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
        // 请求类型
        baseNetWorkEbReqBean.setHttpType((String) interfaceInfo[2]);
        // 请求ID
        baseNetWorkEbReqBean.setInterfaceId((Integer) interfaceInfo[1]);
        // 上下文
        baseNetWorkEbReqBean.setContext(getApplicationContext());
        // 发送网络请求
        BaseOkHttp.onNetWorkFetch(baseNetWorkEbReqBean, new BaseOkHttp.NetWorkMonitor() {
            @Override
            public void onSuccess(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                tvNoData.setVisibility(View.GONE); // 隐藏列表无数据布局
                hideLoading(); // 隐藏弹出框
                callback.onSuccess(baseNetWorkEbRspBean);
            }

            @Override
            public void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                tvNoData.setVisibility(View.GONE); // 隐藏列表无数据布局
                hideLoading(); // 隐藏弹出框
                ToastUtils.showSnackbar(BaseActivityTitle.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
                callback.onError(baseNetWorkEbRspBean);
            }
        });
    }

    /**
     * 初步处理后返回activity
     */
    public void onNetWorkResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解绑
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }
    }
}
