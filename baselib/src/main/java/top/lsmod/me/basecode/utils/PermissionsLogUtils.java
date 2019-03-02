package top.lsmod.me.basecode.utils;

import android.content.Context;

import pub.devrel.easypermissions.EasyPermissions;

public class PermissionsLogUtils {

    private static StringBuffer logStringBuffer = new StringBuffer();

    //使用EasyPermissions查看权限是否已申请
    public static String easyCheckPermissions(Context context, String ... permissions) {
        logStringBuffer.delete(0,logStringBuffer.length());
        for (String permission : permissions) {
            logStringBuffer.append(permission);
            logStringBuffer.append(" is applied? \n     ");
            logStringBuffer.append(EasyPermissions.hasPermissions(context,permission));
            logStringBuffer.append("\n\n");
        }
        return logStringBuffer.toString();
    }

}
