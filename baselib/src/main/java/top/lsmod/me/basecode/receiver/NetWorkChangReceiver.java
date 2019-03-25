package top.lsmod.me.basecode.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import com.orhanobut.logger.Logger;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: 网络状态监听
 * @date: 2019/3/22 11:13 PM
 */
public class NetWorkChangReceiver extends BroadcastReceiver {

    private static int netWorkState;

    public static int LOST_NET_WORK = 10;
    public static int WIFI = 11;
    public static int MOBILE = 12;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 监听wifi的打开与关闭，与wifi的连接无关
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            Log.e("TAG", "wifiState:" + wifiState);
            switch (wifiState) {
                case WifiManager.WIFI_STATE_DISABLED:
                    Logger.d("WIFI_STATE_DISABLED");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Logger.d("WIFI_STATE_DISABLING");
                    break;
            }
        }
        // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            //获取联网状态的NetworkInfo对象
            NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
            if (info != null) {
                //如果当前的网络连接成功并且网络连接可用
                if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                    if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                        // WiFi网络
                        netWorkState = WIFI;
                        Logger.d("连接WiFi成功");
                    } else if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                        // 手机网络
                        netWorkState = MOBILE;
                        Logger.d("切换至手机网络");
                    }
                } else {
                    // 丢失网络
                    netWorkState = LOST_NET_WORK;
                    Logger.d("丢失网络");
                }
            }
        }
    }

    public static int getNetWorkState() {
        return netWorkState;
    }
}