package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.receiver.NetWorkChangReceiver;
import top.lsmod.me.basecode.ui.LoadingDialog;
import top.lsmod.me.basecode.utils.HttpUtils;
import top.lsmod.me.basecode.utils.StatusBarUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Created by yanfulei on 2018/10/4
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseActivityNoTitle extends Activity {
    public String TAG = "YanFulei";
    // 加载框
    private LoadingDialog adDialog;
    // 网络状态
    private boolean isRegistered = false;
    private NetWorkChangReceiver netWorkChangReceiver;
    // 所有布局
    private View llAllView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_activity_no_title);
        llAllView = findViewById(R.id.ll_allview);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(setContentView(), params);
        // 设置导航栏颜色
        StatusBarUtils.setWindowStatusBarColor(this, setStatusBarColor() == 0 ? R.color.white : setStatusBarColor());
        // 注册网络管理器
        initNetStataManger();
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
     * 设置状态栏颜色
     *
     * @return
     */
    public abstract int setStatusBarColor();

    /**
     * 获取所有布局
     */
    public View getLlAllView() {
        return llAllView;
    }

    /**
     * 获取子布局
     */
    public abstract View setContentView();

    /**
     * 发送网络请求demo
     */
//    public void sendNetWorkDemo() {
//        TestInterfaceDemoBean testInterfaceDemoBean = new TestInterfaceDemoBean();
//        testInterfaceDemoBean.setCode("utf-8");
//        testInterfaceDemoBean.setQ("内衣");
//        sendNetWorkRequest(testInterfaceDemoBean, BuildConfig.DEMO_INTERFACE, BaseInterface.TaoBaoDemo);
//    }

    /**
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(Object bean, String serverLocal, Object[] interfaceInfo, boolean isShowLoading) {
        if (isShowLoading) showLoading("正在加载");
        BaseNetWorkEbReqBean baseNetWorkEbReqBean = new BaseNetWorkEbReqBean();
        // 设置上下文
        baseNetWorkEbReqBean.setActivity(this);
        // url请求参数
        if (interfaceInfo[2].equals("get")) {
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
                hideLoading(); // 隐藏弹出框
                onNetWorkResponse(baseNetWorkEbRspBean); // 传递对象
            }

            @Override
            public void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                hideLoading(); // 隐藏弹出框
                ToastUtils.showSnackbar(BaseActivityNoTitle.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
                onNetWorkResponse(baseNetWorkEbRspBean);
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
        if (interfaceInfo[2].equals("get")) {
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
                hideLoading(); // 隐藏弹出框
                callback.onSuccess(baseNetWorkEbRspBean); // 传递对象
            }

            @Override
            public void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {
                hideLoading(); // 隐藏弹出框
                ToastUtils.showSnackbar(BaseActivityNoTitle.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
                callback.onError(baseNetWorkEbRspBean);
            }
        });
    }

    /**
     * 初步处理后返回activity
     */
    public void onNetWorkResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {

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

    public void hideLoading() {
        if (null != adDialog) {
            adDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isRegistered) {
            unregisterReceiver(netWorkChangReceiver);
        }
    }
}
