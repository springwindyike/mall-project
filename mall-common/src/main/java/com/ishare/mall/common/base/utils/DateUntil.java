package com.ishare.mall.common.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by wanghao on 2015/11/12.
 */
public class DateUntil {

    public static void main(String[] str){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
//        calendar.setTimeInMillis(999);
        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DATE));
//        Date nowDate = new Date();
//        Calendar now = Calendar.getInstance();
//        now.setTime(nowDate);
//        System.out.println(now.get(Calendar.YEAR));
        System.out.println(calendar.getTime());
    }

    public static Date getTheEndOfDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE)-1);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static String getDateStr(String format,Date date){
        if (StringUtils.isEmpty(format)){
            format = "yyyy-dd-mm HH:mm:SS";
        }
        if (date == null){
            return null;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
       return simpleDateFormat.format(date);
    }
    // 根据日期取得星期几
    public static String getWeek(Date date) {
        String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(week_index<0) {
            week_index = 0;
        }
        return weeks[week_index];
    }
}
