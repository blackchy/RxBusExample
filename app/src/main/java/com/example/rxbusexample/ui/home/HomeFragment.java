package com.example.rxbusexample.ui.home;

import android.widget.TextView;
import butterknife.BindView;
import com.example.rxbusexample.R;
import com.example.rxbusexample.model.MessageEvent;
import com.example.rxbusexample.rx.RxBus;
import com.example.rxbusexample.ui.BaseFragment;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class HomeFragment extends BaseFragment {
  @BindView(R.id.tv_home_accept) TextView tvHomeAccept;

  @Override protected int layoutResId() {
    return R.layout.fragment_home;
  }

  @Override protected void initData() {

  }

  @Override protected void initView() {
    addDisposable(RxBus.getInstance()
        .toObservable(MessageEvent.class)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<MessageEvent>() {
          @Override public void accept(MessageEvent messageEvent) throws Exception {
            tvHomeAccept.setText(messageEvent.getTime() + "\n接收到 " + messageEvent.getMessage());
          }
        }));
  }
}
