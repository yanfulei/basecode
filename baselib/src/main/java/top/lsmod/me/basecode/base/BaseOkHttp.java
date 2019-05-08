package top.lsmod.me.basecode.base;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbReqBean;
import top.lsmod.me.basecode.eventbus.bean.BaseNetWorkEbRspBean;
import top.lsmod.me.basecode.utils.CacheUtils;

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
    private static final String TAG = "lsmod";
    private static String token;

    /**
     * 网络请求到达
     *
     * @param baseNetWorkEbReqBean
     */
    public static void onNetWorkFetch(BaseNetWorkEbReqBean baseNetWorkEbReqBean, NetWorkMonitor netWorkMonitor) {
        try {
            token = CacheUtils.get(baseNetWorkEbReqBean.getContext()).getAsString("token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 网络是否可用
//        if (NetWorkChangReceiver.getNetWorkState() == NetWorkChangReceiver.LOST_NET_WORK) {
//            ToastUtils.showToast(baseNetWorkEbReqBean.getActivity(), "当前网络不可用!", ToastUtils.ERROR);
//            return;
//        }
        if (baseNetWorkEbReqBean.getHttpType().equals("get")) {
            AsyncGet(baseNetWorkEbReqBean, netWorkMonitor);
        } else if (baseNetWorkEbReqBean.getHttpType().equals("post")) {
            AsyncPostJson(baseNetWorkEbReqBean, netWorkMonitor);
        } else if (baseNetWorkEbReqBean.getHttpType().equals("auth")) {
            AsyncAuth(baseNetWorkEbReqBean, netWorkMonitor);
        } else if (baseNetWorkEbReqBean.getHttpType().equals("delete")) {
            AsyncDelete(baseNetWorkEbReqBean, netWorkMonitor);
        }
        Logger.d("请求taoken>>" + "Authorization:" + "bearer " + token);
    }

    /**
     * 异步GET请求
     */
    private static void AsyncGet(BaseNetWorkEbReqBean baseNetWorkEbReqBean, NetWorkMonitor netWorkMonitor) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
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
                    netWorkMonitor.onError(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful() || response.code() != 200) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        netWorkMonitor.onError(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        netWorkMonitor.onSuccess(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * 异步DELETE请求
     */
    private static void AsyncDelete(BaseNetWorkEbReqBean baseNetWorkEbReqBean, NetWorkMonitor netWorkMonitor) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
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
                    netWorkMonitor.onError(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful() || response.code() != 200) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        netWorkMonitor.onError(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        netWorkMonitor.onSuccess(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * 异步POST请求
     */
    private static void AsyncPostJson(BaseNetWorkEbReqBean baseNetWorkEbReqBean, NetWorkMonitor netWorkMonitor) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
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
                    netWorkMonitor.onError(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful() || response.code() != 200) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        netWorkMonitor.onError(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        netWorkMonitor.onSuccess(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    /**
     * Auth 2.0 鉴权
     */
    private static void AsyncAuth(BaseNetWorkEbReqBean baseNetWorkEbReqBean, NetWorkMonitor netWorkMonitor) {
        Gson gson = new Gson();
        Map<String, String> dataMap = gson.fromJson(baseNetWorkEbReqBean.getJson(), Map.class);
        // 拼接参数
        String username = dataMap.get("username");
        String password = dataMap.get("password");
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
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
                    netWorkMonitor.onError(baseNetWorkEbRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Logger.d(baseNetWorkEbReqBean.getUrl() + "出参>>" + responseStr);
                    if (!response.isSuccessful() && response.code() != 200) {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(false);
                        netWorkMonitor.onError(baseNetWorkEbRspBean);
                    } else {
                        BaseNetWorkEbRspBean baseNetWorkEbRspBean = new BaseNetWorkEbRspBean();
                        baseNetWorkEbRspBean.setHttpCode(response.code());
                        baseNetWorkEbRspBean.setHttpMsg(responseStr);
                        baseNetWorkEbRspBean.setInterfaceId(baseNetWorkEbReqBean.getInterfaceId());
                        baseNetWorkEbRspBean.setSuccess(true);
                        netWorkMonitor.onSuccess(baseNetWorkEbRspBean);
                    }
                });
            }
        });
    }

    public interface NetWorkMonitor {
        void onSuccess(BaseNetWorkEbRspBean baseNetWorkEbRspBean);

        void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean);
    }

    public interface RealTimeNetWorkMonitor {
        void onSuccess(BaseNetWorkEbRspBean baseNetWorkEbRspBean);

        void onError(BaseNetWorkEbRspBean baseNetWorkEbRspBean);
    }
}
