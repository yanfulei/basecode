package top.lsmod.me.basecode.utils;

import java.text.DateFormat;
import java.text.ParseException;
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
    public static final String YYYY_MM_DD_HH_MM_SS_FILE_NAME = "yyyy_MM_dd_HH_mm_ss";

    /**
     * 获取当天的开始和结束时间
     */
    public static long[] getDayStartAndEnd(Calendar calendar) {
        long[] strToday = new long[2];
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long time = calendar.getTimeInMillis() / 1000;
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        long timeend = calendar.getTimeInMillis() / 1000;
        strToday[0] = time;
        strToday[1] = timeend;
        return strToday;
    }

    /**
     * 获取今天的开始时间
     */
    public static int getStartTimeStamp() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        long time = todayStart.getTimeInMillis() / 1000;
        return (int) time;
    }

    /**
     * 获取今天的结束时间
     */
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
     * 获取某一天的开始时间
     */
    public static long getSomeDayStartTimeStamp(int year, int mount, int day) {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(year, mount, day);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        return todayStart.getTimeInMillis();
    }

    /**
     * 获取某一天的结束时间
     */
    public static long getSomeDayEndTimeStamp(int year, int mount, int day) {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(year, mount, day);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTimeInMillis();
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

    /**
     * strTime要转换的String类型的时间
     * formatType时间格式
     * strTime的时间格式和formatType的时间格式必须相同
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static long stringToLong(String strTime, String formatType) {
        Date date = null; // String类型转成date类型
        try {
            date = stringToDate(strTime, formatType);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }

    /**
     * strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
     * HH时mm分ss秒，
     * strTime的时间格式必须要与formatType的时间格式相同
     *
     * @param strTime
     * @param formatType
     * @return
     * @throws ParseException
     */
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    /**
     * date要转换的date类型的时间
     *
     * @param date
     * @return
     */
    public static long dateToLong(Date date) {
        return date.getTime();
    }
}
