package com.example.rxbusexample.ui.home;

import android.view.View;
import android.widget.Button;
import butterknife.BindView;
import com.example.rxbusexample.R;
import com.example.rxbusexample.model.MessageEvent;
import com.example.rxbusexample.rx.RxBus;
import com.example.rxbusexample.ui.BaseFragment;
import com.example.rxbusexample.util.DateUtil;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class ServiceFragment extends BaseFragment {
  @BindView(R.id.btn_send) Button btnSend;

  @Override protected int layoutResId() {
    return R.layout.fragment_service;
  }

  @Override protected void initData() {
  }

  @Override protected void initView() {
    btnSend.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        RxBus.getInstance().post(new MessageEvent(DateUtil.getCurrentTime(), "服务界面的事件"));
      }
    });
  }
}