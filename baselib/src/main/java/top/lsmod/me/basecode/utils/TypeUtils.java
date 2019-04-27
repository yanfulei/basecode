package top.lsmod.me.basecode.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * @author: yanfulei
 * @email: yanfulei1990@gmail.com
 * @description: 基本类型转换utils
 * @date: 2019/3/26 7:27 PM
 */
public class TypeUtils {

    /**
     * String 转换为 Double
     *
     * @param text
     * @return
     */
    public static Double StringToDoubule(String text) {
        Double d = Double.valueOf(0);
        try {
            d = TextUtils.isEmpty(text) ? 0 : Double.valueOf(text);
        } catch (Exception e) {
            e.printStackTrace();
            return d;
        }
        return d;
    }

    /**
     * String 转换为 Integer
     *
     * @param text
     * @return
     */
    public static Integer StringToInteger(String text) {
        Integer d = 0;
        try {
            d = TextUtils.isEmpty(text) ? 0 : Integer.valueOf(text);
        } catch (Exception e) {
            e.printStackTrace();
            return d;
        }
        return d;
    }

    /**
     * List to join string
     *
     * @param list
     * @param split
     * @return
     */
    public static String ListToJoinString(List<String> list, String split) {
        if (list.isEmpty()) {
            return "";
        }
        StringBuilder csvBuilder = new StringBuilder();
        for (String city : list) {
            csvBuilder.append(city);
            csvBuilder.append(split);
        }
        String csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - split.length());
        return csv;
    }

    /**
     * Array to join string
     *
     * @param list
     * @param split
     * @return
     */
    public static String ArrayToJoinString(String[] list, String split) {
        StringBuilder csvBuilder = new StringBuilder();
        for (String city : list) {
            csvBuilder.append(city);
            csvBuilder.append(split);
        }
        String csv = csvBuilder.toString();
        csv = csv.substring(0, csv.length() - split.length());
        return csv;
    }
}
