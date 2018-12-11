package com.example.rxbusexample.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
  private CompositeDisposable mCompositeDisposable;
  private Unbinder unbinder;
  protected Context mContext;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mContext = this;
    mCompositeDisposable = new CompositeDisposable();
    setContentView(layoutResId());
    unbinder = ButterKnife.bind(this);
    onInit();
  }

  protected abstract void onInit();

  protected abstract int layoutResId();

  @Override protected void onDestroy() {
    super.onDestroy();
    if (unbinder != null) {
      unbinder.unbind();
    }
    clearDisposable();
  }

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
