package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import top.lsmod.me.basecode.R;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.utils.HttpUtils;
import top.lsmod.me.basecode.utils.StatusBarUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

import static android.view.Window.PROGRESS_START;

/**
 * Created by yanfulei on 2018/10/5
 * Email yanfulei1990@gmail.com
 */
public abstract class BaseLauncherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_launcher);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addContentView(setContentView(), params);
        // 设置导航栏颜色
        StatusBarUtils.setWindowStatusBarColor(this, R.color.white);
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
     * 发送网络接口请求
     */
    public void sendNetWorkRequest(BaseReqBean bean, String serverLocal, Object[] interfaceInfo) {
        if (!EventBus.getDefault().isRegistered(this)) {
            // EventBus注册
            EventBus.getDefault().register(this);
        }
        // OKHTTP注册
        new BaseOkHttp().initNetWorkPlugin();
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
//        hideLoading();
        // 错误自动弹出
        if (baseNetWorkEbRspBean.isAuto()) {
            if (!baseNetWorkEbRspBean.isSuccess()) {
                ToastUtils.showToast(this, baseNetWorkEbRspBean.getHttpMsg(), ToastUtils.ERROR);
                return;
            }
            // 业务层是否错误
            Gson gson = new Gson();
            BaseRspBean baseRspBean = gson.fromJson(baseNetWorkEbRspBean.getHttpMsg(), BaseRspBean.class);
            if (!baseRspBean.Success){
                ToastUtils.showToast(this, baseRspBean.getMessage(), ToastUtils.ERROR);
                return;
            }
        }
        onNetWorkResponse(baseNetWorkEbRspBean);
    }

    /**
     * 初步处理后返回activity
     */
    public void onNetWorkResponse(BaseNetWorkEbRspBean baseNetWorkEbRspBean){

    }
}