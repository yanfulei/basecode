package top.lsmod.me.basecode.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间处理工具类
 */
public class BDateUtils {

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String HH_MM = "HH:mm";
    public static final String HH_MM_SS = "HH:mm:ss";

    /**
     * 获取当天的开始和结束时间
     */
    public static long[] getDayStartAndEnd(Calendar calendar) {
        long[] strToday = new long[2];
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long time = calendar.getTimeInMillis()/1000;
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        long timeend = calendar.getTimeInMillis() / 1000;
        strToday[0] = time;
        strToday[1] = timeend;
        return strToday;
    }

    public static int getStartTimeStamp() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        long time = todayStart.getTimeInMillis()/1000;
        return (int)time;
    }


    public static int getEndTimeStamp() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        long time = todayEnd.getTimeInMillis() / 1000;
        return (int) time;
    }

    /**
     * 日期字符串转换为日期
     */
    public synchronized static Date getDate(String dateStr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(YYYY_MM_DD);
            return df.parse(dateStr);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 将毫秒时间戳转成日期
     *
     * @param timeStamp 时间戳
     * @return
     */
    public static String getDateStr(long timeStamp, String formart) {
        if (timeStamp == 0) {
            return "";
        }
        Date date = new Date(timeStamp);
        DateFormat formatter;
        formatter = new SimpleDateFormat(formart);//HH24小时制  hh 12小时制
        return formatter.format(date);
    }
}
