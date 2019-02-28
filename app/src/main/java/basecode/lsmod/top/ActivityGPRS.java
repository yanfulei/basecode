package basecode.lsmod.top;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import top.lsmod.me.basecode.base.BaseActivityTitle;
import top.lsmod.me.basecode.utils.LocationUtils;
import top.lsmod.me.basecode.utils.ToastUtils;

/**
 * Author:yanfulei
 * Date:2018/12/2
 * Email:yanfulei1990@gmail.com
 **/
public class ActivityGPRS extends BaseActivityTitle {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Location location = LocationUtils.getInstance(this).showLocation();
        if (location != null) {
            String address = "纬度：" + location.getLatitude() + "经度：" + location.getLongitude();
            Log.d("FLY.LocationUtils", address);
            ToastUtils.showToast(this, address, ToastUtils.SUCCESS);
        }
    }

    @Override
    public int setStatusBarColor() {
        return 0;
    }

    @Override
    public View setContentView() {
        return getLayoutInflater().inflate(R.layout.activity_gprs, null);
    }

    @Override
    public String setTitleBarText() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtils.getInstance(this).removeLocationUpdatesListener();
    }
}
