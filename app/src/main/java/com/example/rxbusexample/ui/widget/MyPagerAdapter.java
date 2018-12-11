package com.example.rxbusexample.ui.widget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;
import com.example.rxbusexample.ui.BaseFragment;
import java.util.List;

/**
 * Created by haiyu.chen on 2018/12/11.
 *
 * 懒加载
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

  private FragmentManager fragmentManager;
  private List<BaseFragment> list;

  public MyPagerAdapter(FragmentManager fm, List<BaseFragment> list) {
    super(fm);
    this.fragmentManager = fm;
    this.list = list;
  }

  @Override public Fragment getItem(int position) {
    return list.get(position);
  }

  @Override public int getCount() {
    return list != null ? list.size() : 0;
  }

  @Override public Fragment instantiateItem(ViewGroup container, int position) {
    Fragment fragment = (Fragment) super.instantiateItem(container, position);
    fragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
    return fragment;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    Fragment fragment = list.get(position);
    fragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
  }
}
