package top.lsmod.me.basecode.base;

import android.content.Context;

import java.io.File;

import top.lsmod.me.basecode.utils.FileUtil;

/**
 * Created by Yanfulei on 2017/2/22.
 */

public class BaseAppConfig {
    private volatile static BaseAppConfig instance;
    /**
     * App根目录.
     */
    public static String APP_PATH_ROOT;
    /**
     * 下载文件目录
     */
    public static String DOWNLOAD_PATH;
    public static String IMAGE_PATH;
    public static String CACHE_PATH;
    public static boolean getCachePath = false;
    //设置服务器地址
    public static String SERVICE_PATH = " ";
    //SharedPreferences  name
    public static String SP_NAME = "fieldsys";

    public static void init(Context context, String fileName) {
        APP_PATH_ROOT = FileUtil.getInstance().getRootPath().getAbsolutePath() + File.separator + fileName;
        DOWNLOAD_PATH = APP_PATH_ROOT + File.separator + "downLoad";
        CACHE_PATH = APP_PATH_ROOT + File.separator + "cache";
        IMAGE_PATH = APP_PATH_ROOT + File.separator + "image";
        initFile();
    }

    public static void initFile() {
        FileUtil.getInstance().initDirectory(APP_PATH_ROOT);
        getCachePath = FileUtil.getInstance().initDirectory(CACHE_PATH);
        FileUtil.getInstance().initDirectory(DOWNLOAD_PATH);
        FileUtil.getInstance().initDirectory(IMAGE_PATH);
    }

}
