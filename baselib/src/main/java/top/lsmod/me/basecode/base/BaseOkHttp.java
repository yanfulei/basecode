package top.lsmod.me.basecode.base;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.utils.SPUtils;

/**
 * OKHttp网络请求基类
 */
public class BaseOkHttp {
    private static Handler mMainHandler = new Handler(Looper.getMainLooper());
    // Markdown
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    // Json
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType MEDTA_TYPE_FORM = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
    //    private static OkHttpClient client;
    private static final String TAG = "XPORT";
    private static boolean okHttpIsRegistered = false;
    // token
    private static String token;

    public void initNetWorkPlugin(Activity activity) {
        if (!okHttpIsRegistered) {
            EventBus.getDefault().register(this);
            okHttpIsRegistered = true;
        }
        // 初始化token信息
        Auth20RspBean auth20RspBean = (Auth20RspBean) SPUtils.getInstance().readObject(activity, "auth_info");
        if (null != auth20RspBean) {
            token = auth20RspBean.getAccess_token();
        }
    }

    /**
     * 网络请求到达
     *
     * @param baseNetWorkEbReqBean
     */
    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onNetWorkFetch(BaseNetWorkEbReqBean baseNetWorkEbReqBean) {
        if (baseNetWorkEbReqBean.getHttpType().equals("get")) {
            AsyncGet(baseNetWorkEbReqBean);
        } else if (baseNetWorkEbReqBean.getHttpType().equals("post")) {
            AsyncPostJson(baseNetWorkEbReqBean);
        } else if (baseNetWorkEbReqBean.getHttpType().equals("auth")) {
            AsyncAuth(baseNetWorkEbReqBean);
        } else if (baseNetWorkEbReqBean.getHttpType().equals("delete")) {
            AsyncDelete(baseNetWorkEbReqBean);
        }
        Logger.d("请求taoken>>" + "Authorization:" + "bearer " + token);
    }

    /**
     * 异步GET请求
     */
    public void AsyncGet(BaseNetWorkEbReqBean baseNetWorkEbReqBean) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseNetWorkEbReqBean.getUrl())
                .addHeader("Authorization", "bearer " + token)
                .build();
        Logger.d(baseNetWorkEbReqBean.getUrl() + "入参>>" + baseNetWorkEbReqBean.getUrl());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + e.getMessage());
                    BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                    baseNetWorkEbRspBean.setHttpCode(503);
                    baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                    baseNetWorkEbRspBean.setHttpMsg(e.getMessage());
                    baseNetWorkEbRspBean.setSuccess(false);
                    EventBus.getDefault().post(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful()) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * 异步DELETE请求
     */
    public void AsyncDelete(BaseNetWorkEbReqBean baseNetWorkEbReqBean) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseNetWorkEbReqBean.getUrl())
                .addHeader("Authorization", "bearer " + token)
                .delete()
                .build();
        Logger.d(baseNetWorkEbReqBean.getUrl() + "入参>>" + baseNetWorkEbReqBean.getUrl());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + e.getMessage());
                    BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                    baseNetWorkEbRspBean.setHttpCode(503);
                    baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                    baseNetWorkEbRspBean.setHttpMsg(e.getMessage());
                    baseNetWorkEbRspBean.setSuccess(false);
                    baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                    EventBus.getDefault().post(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful()) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * 异步POST请求
     */
    public static void AsyncPostJson(BaseNetWorkEbReqBean baseNetWorkEbReqBean) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseNetWorkEbReqBean.getUrl())
                .addHeader("Authorization", "bearer " + token)
                .post(RequestBody.create(MEDIA_TYPE_JSON, baseNetWorkEbReqBean.getJson()))
                .build();
        Logger.d(baseNetWorkEbReqBean.getUrl() + "入参>>" + baseNetWorkEbReqBean.getJson());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + e.getMessage());
                    BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                    baseNetWorkEbRspBean.setHttpCode(503);
                    baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                    baseNetWorkEbRspBean.setHttpMsg(e.getMessage());
                    baseNetWorkEbRspBean.setSuccess(false);
                    baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                    EventBus.getDefault().post(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful()) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * Auth 2.0 鉴权
     */
    public static void AsyncAuth(BaseNetWorkEbReqBean baseNetWorkEbReqBean) {
        Gson gson = new Gson();
        Map<String, String> dataMap = gson.fromJson(baseNetWorkEbReqBean.getJson(), Map.class);
        // 拼接参数
        String username = dataMap.get("username");
        String password = dataMap.get("password");
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(baseNetWorkEbReqBean.getUrl())
                .addHeader("granttype", "mobile")
                .post(RequestBody.create(MEDTA_TYPE_FORM, "username=" + username + "&password=" + password + "&grant_type=password"))
                .build();
        Logger.d(baseNetWorkEbReqBean.getUrl() + "入参>>" + "username=" + username + "&password=" + password + "&grant_type=password");
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + e.getMessage());
                    BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                    baseNetWorkEbRspBean.setHttpCode(503);
                    baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                    baseNetWorkEbRspBean.setHttpMsg(e.getMessage());
                    baseNetWorkEbRspBean.setSuccess(false);
                    baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                    EventBus.getDefault().post(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful()) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        baseNetWorkEbRspBean.setAuto(baseNetWorkEbReqBean.isAuto());
                        EventBus.getDefault().post(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * 刷新token
     */
    public static void AsyncRefreshToken(final Activity activity, String url, final IOkHttpResponse callback) {
        OkHttpClient client = new OkHttpClient();
        // 判断网络是否可用
//        if (!AppManager.isNetworkConnected(activity)) {
//            callback.onFailure(503, "请检查您的网络连接状态，接入有线后，请重启设备！");
//            return;
//        }
//        String encodedString = Base64.encodeToString("00102:1c456ff6131d4b7da528d2bef3df50cc".getBytes(), Base64.NO_WRAP);
//        String webForms = "grant_type=refresh_token&refresh_token=" + BaseValue.REFRESH_TOKEN + "";
        Request request = new Request.Builder()
                .url(url)
//                .addHeader("authorization", "Basic " + encodedString)
//                .post(RequestBody.create(MEDTA_TYPE_FORM, webForms))
                .build();
        Log.d(TAG, "Post-Url: " + url);
//        Log.d(TAG, "AsyncPostJson: " + webForms);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMainHandler.post(() -> callback.onFailure(503, e.getMessage()));
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    if (!response.isSuccessful()) {
                        callback.onFailure(response.code(), responseStr);
                    } else {
                        callback.onSuccess(response.code(), responseStr);
                    }
                });
            }
        });
    }

    public interface IOkHttpResponse {
        void onFailure(int httpCode, String errorMsg);

        void onSuccess(int httpCode, String responseBody);
    }
}
