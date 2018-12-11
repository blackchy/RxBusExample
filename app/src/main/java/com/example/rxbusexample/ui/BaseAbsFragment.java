package com.example.rxbusexample.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class BaseAbsFragment extends Fragment {
  private boolean isFragmentVisible;
  private boolean isReuseView;
  private boolean isFirstVisible;
  private View rootView;

  @Override public void setUserVisibleHint(boolean isVisibleToUser) {
    super.setUserVisibleHint(isVisibleToUser);
    if (rootView == null) {
      return;
    }
    if (isFirstVisible && isVisibleToUser) {
      onFragmentFirstVisible();
      isFirstVisible = false;
    }
    if (isVisibleToUser) {
      onFragmentVisibleChange(true);
      isFragmentVisible = true;
      return;
    }
    if (isFragmentVisible) {
      isFragmentVisible = false;
      onFragmentVisibleChange(false);
    }
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initVariable();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    if (rootView == null) {
      rootView = view;
      if (getUserVisibleHint()) {
        if (isFirstVisible) {
          onFragmentFirstVisible();
          isFirstVisible = false;
        }
        onFragmentVisibleChange(true);
        isFragmentVisible = true;
      }
    }
    super.onViewCreated(isReuseView && rootView != null ? rootView : view, savedInstanceState);
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    initVariable();
  }

  private void initVariable() {
    isFirstVisible = true;
    isFragmentVisible = false;
    rootView = null;
    isReuseView = true;
  }

  protected void reuseView(boolean isReuse) {
    isReuseView = isReuse;
  }

  protected void onFragmentVisibleChange(boolean isVisible) {

  }

  protected void onFragmentFirstVisible() {

  }

  protected boolean isFragmentVisible() {
    return isFragmentVisible;
  }
}
