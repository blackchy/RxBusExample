package com.example.rxbusexample.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class DateUtil {
  private static Date date;
  private static SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static String getCurrentTime() {
    date = new Date();
    return timeSdf.format(date);
  }
}
