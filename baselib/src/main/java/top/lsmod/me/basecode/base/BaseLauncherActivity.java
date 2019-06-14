package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.ui.LoadingDialog;
import top.lsmod.me.basecode.utils.HttpUtils;
import top.lsmod.me.basecode.utils.StatusBarUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

import static android.view.Window.PROGRESS_START;

/**
 * Created by yanfulei on 2018/10/5
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseLauncherActivity extends Activity {
    public String TAG = "YanFulei";
    // 加载框
    private LoadingDialog adDialog;
    // 所有布局
    private View llAllView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_launcher);
        llAllView = findViewById(R.id.ll_allview);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(setContentView(), params);
        // 设置导航栏颜色
        StatusBarUtils.setWindowStatusBarColor(this, setStatusBarColor() == 0 ? R.color.white : setStatusBarColor());
        // 延迟3秒启动
        mHandler.sendEmptyMessageDelayed(PROGRESS_START, 3000);
    }

    /**
     * 获取子布局
     */
    public abstract View setContentView();

    /**
     * 初始化布局
     */
    public abstract void initData();

    private Handler mHandler = new Handler(msg -> {
        initData();
        return false;
    });

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
        adDialog.dimEnabled(false);
        adDialog.show();
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
                ToastUtils.showSnackbar(BaseLauncherActivity.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
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
                ToastUtils.showSnackbar(BaseLauncherActivity.this, getLlAllView(), baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
                callback.onError(baseNetWorkEbRspBean);
            }
        });
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
     * 初步处理后返回activity
     */
    public void onNetWorkResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean) {

    }

    public View getLlAllView() {
        return llAllView;
    }

    /**
     * 设置状态栏颜色
     *
     * @return
     */
    public abstract int setStatusBarColor();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
