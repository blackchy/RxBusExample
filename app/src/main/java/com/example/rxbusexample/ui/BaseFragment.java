package com.example.rxbusexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public abstract class BaseFragment extends BaseAbsFragment {
  private CompositeDisposable mCompositeDisposable;
  private View rootView;
  private Unbinder unbinder;
  protected Context mContext;

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    rootView = inflater.inflate(layoutResId(), container, false);
    unbinder = ButterKnife.bind(this, rootView);
    mCompositeDisposable = new CompositeDisposable();
    initView();
    return rootView;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    mContext = context;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    if (unbinder != null) {
      unbinder.unbind();
    }
    clearDisposable();
  }

  @Override protected void onFragmentFirstVisible() {
    super.onFragmentFirstVisible();
    initData();
  }

  protected abstract void initView();

  protected abstract void initData();

  protected abstract int layoutResId();

  protected void addDisposable(Disposable mDisposable) {
    if (mCompositeDisposable == null) {
      mCompositeDisposable = new CompositeDisposable();
    }
    mCompositeDisposable.add(mDisposable);
  }

  private void clearDisposable() {
    if (mCompositeDisposable != null) {
      mCompositeDisposable.clear();
    }
  }
}
