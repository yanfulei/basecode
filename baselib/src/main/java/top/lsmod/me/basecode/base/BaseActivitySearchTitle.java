package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.FrameLayout;
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
import top.lsmod.me.basecode.utils.MyViewUtil;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Created by yanfulei on 2018/10/4
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseActivitySearchTitle extends Activity {
    public String TAG = "YanFulei";
    private CommonTitleBar commonTitleBar;
    // 加载框
    private LoadingDialog adDialog;
    // 内容区域
    private LinearLayout llContent;
    // 所有布局
    private LinearLayout llAllView;
    // 列表没有数据展示
    private TextView tvNoData;
    // 内容布局
    private FrameLayout fl_content;
    // 网络状态
    private boolean isRegistered = false;
    private NetWorkChangReceiver netWorkChangReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_activity_search_title);
        initParentView();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        View view = setContentView();
        commonTitleBar = findViewById(R.id.titlebar);
        llContent = findViewById(R.id.ll_content);
        llAllView = findViewById(R.id.ll_allview);
        tvNoData = findViewById(R.id.tv_no_data);
        fl_content = findViewById(R.id.fl_content);
        // 设置是否全屏
        if (isFullScreen()) {
            MyViewUtil.setMargins(fl_content, 0, 0, 0, 0);
        } else {
            int hight = MyViewUtil.getStatusBarHeight(this);
            int hight1 = MyViewUtil.dip2px(this, 44);
            MyViewUtil.setMargins(fl_content, 0, hight + hight1 - 1, 0, 0);
        }
        llContent.addView(view, params);
        // 注册网络管理器
        initNetStataManger();
    }

    private void initParentView() {
        commonTitleBar.getCenterSearchEditText().setOnKeyListener((view, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEND
                    || i == EditorInfo.IME_ACTION_DONE
                    || (keyEvent != null && KeyEvent.KEYCODE_ENTER == keyEvent.getKeyCode() && KeyEvent.ACTION_DOWN == keyEvent.getAction())) {
                onTitleSearch(commonTitleBar.getSearchKey());
            }
            return true;
        });
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
     * 触发头部检索
     *
     * @param key
     */
    public abstract void onTitleSearch(String key);

    /**
     * 获取所有布局
     */
    public LinearLayout getLlAllView() {
        return llAllView;
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
     * 传递CommonTitleBar对象
     *
     * @param commonTitleBar
     */
    public void giveCommonTitleBarObj(CommonTitleBar commonTitleBar) {

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
    public void showLoading(String msg) {
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
        sendNetWorkRequest(testInterfaceDemoBean, BuildConfig.DEMO_INTERFACE, BaseInterface.TaoBaoDemo, true);
    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(Object bean, String serverLocal, Object[] interfaceInfo, boolean isShowLoading) {
        if (isShowLoading) showLoading("正在加载");
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
                ToastUtils.showSnackbar(BaseActivitySearchTitle.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
            }
        });
    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(Object bean, String serverLocal, Object[] interfaceInfo, boolean isShowLoading,
                                   BaseOkHttp.RealTimeNetWorkMonitor callback) {
        if (isShowLoading) showLoading("正在加载");
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
        baseNetWorkEbReqBean.setContext(getApplicationContext());
        // 发送网络请求
        BaseOkHttp.onNetWorkFetch(baseNetWorkEbReqBean, new BaseOkHttp.NetWorkMonitor() {
            @Override
            public void onSuccess(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                tvNoData.setVisibility(View.GONE); // 隐藏列表无数据布局
                hideLoading(); // 隐藏弹出框
                callback.onSuccess(baseNetWorkEbRspBean); // 传递对象
            }

            @Override
            public void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                tvNoData.setVisibility(View.GONE); // 隐藏列表无数据布局
                hideLoading(); // 隐藏弹出框
                ToastUtils.showSnackbar(BaseActivitySearchTitle.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
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
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }
    }
}
