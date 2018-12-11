package com.example.rxbusexample.ui.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;

/**
 * Created by haiyu.chen on 2018/12/11.
 *
 * 禁止viewpager滑动切换
 */

public class MyViewPager extends ViewPager {
  public MyViewPager(Context context) {
    super(context);
  }

  public MyViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override public boolean executeKeyEvent(KeyEvent event) {
    return false;
  }

  @Override public boolean onTouchEvent(MotionEvent ev) {
    return false;
  }
}
