package cn.cckoo.yu.common.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Date {
    public static Timestamp getNowTimeStamp() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static SimpleDateFormat systemDateMonthStyle = new SimpleDateFormat("yyyy-MM");

    public static SimpleDateFormat systemDateStyle = new SimpleDateFormat("yyyy-MM-dd");

    public static SimpleDateFormat systemDateTimeStyle = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static HashMap<Integer, Integer> argsMap = new HashMap<Integer, Integer>(){
        {
            put(0, Calendar.HOUR_OF_DAY);
            put(1, Calendar.MINUTE);
            put(2, Calendar.SECOND);
            put(3, Calendar.DAY_OF_MONTH);
            put(4, Calendar.MONTH);
            put(5, Calendar.YEAR);
        }
    };

    public static Timestamp calculateTime(int field, int second, Timestamp timestamp) {
        timestamp = timestamp == null ? getNowTimeStamp() : timestamp;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        calendar.add(field, second);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static Timestamp buildTime(Integer... args) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MILLISECOND, 0);
        int length = Math.min(args.length, argsMap.size());
        for (int i = 0; i < length; i++)
            calendar.set(argsMap.get(i), args[i]);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public static int getField(java.util.Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }
}
