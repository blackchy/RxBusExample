package com.example.rxbusexample.ui.home;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.animation.AccelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import butterknife.BindView;
import com.example.rxbusexample.R;
import com.example.rxbusexample.ui.BaseActivity;
import com.example.rxbusexample.ui.BaseFragment;
import com.example.rxbusexample.ui.widget.FixedSpeedScroller;
import com.example.rxbusexample.ui.widget.MyPagerAdapter;
import com.example.rxbusexample.ui.widget.MyViewPager;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class HomeActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
  @BindView(R.id.rb_menu1) RadioButton rbMenu1;
  @BindView(R.id.rb_menu2) RadioButton rbMenu2;
  @BindView(R.id.rb_menu3) RadioButton rbMenu3;
  @BindView(R.id.rb_menu4) RadioButton rbMenu4;
  @BindView(R.id.vp_content) MyViewPager vpContent;
  private List<BaseFragment> fragments = new ArrayList<>();
  private FragmentManager fragmentManager = getSupportFragmentManager();
  private MyPagerAdapter pagerAdapter;

  @Override protected int layoutResId() {
    return R.layout.activity_home;
  }

  @Override protected void onInit() {
    fragments.add(new HomeFragment());
    fragments.add(new ServiceFragment());
    fragments.add(new StoreFragment());
    fragments.add(new PersonalFragment());
    pagerAdapter = new MyPagerAdapter(fragmentManager, fragments);
    vpContent.setAdapter(pagerAdapter);
    setViewPagerSpeed(vpContent, 300);
    rbMenu1.setOnCheckedChangeListener(this);
    rbMenu2.setOnCheckedChangeListener(this);
    rbMenu3.setOnCheckedChangeListener(this);
    rbMenu4.setOnCheckedChangeListener(this);
  }

  @Override public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
    switch (compoundButton.getId()) {
      case R.id.rb_menu1:
        if (b) {
          vpSelect(0);
        }
        break;
      case R.id.rb_menu2:
        if (b) {
          vpSelect(1);
        }
        break;
      case R.id.rb_menu3:
        if (b) {
          vpSelect(2);
        }
        break;
      case R.id.rb_menu4:
        if (b) {
          vpSelect(3);
        }
        break;
    }
  }

  private int selected = -1;

  private void vpSelect(int position) {
    if (selected == position) {
      return;
    }
    selected = position;
    vpContent.setCurrentItem(position);
  }

  protected void setViewPagerSpeed(ViewPager viewPager, int mDuration) {
    try {
      Field field = ViewPager.class.getDeclaredField("mScroller");
      field.setAccessible(true);
      FixedSpeedScroller scroller =
          new FixedSpeedScroller(viewPager.getContext(), new AccelerateInterpolator());
      field.set(viewPager, scroller);
      scroller.setmDuration(mDuration);
    } catch (Exception e) {
    }
  }
}
