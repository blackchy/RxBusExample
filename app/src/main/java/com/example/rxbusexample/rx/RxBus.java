package com.example.rxbusexample.rx;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class RxBus {

  private static volatile RxBus instance = null;

  private RxBus() {
  }

  public static RxBus getInstance() {
    if (instance == null) {
      synchronized (RxBus.class) {
        if (instance == null) {
          instance = new RxBus();
        }
      }
    }
    return instance;
  }

  private Subject<Object> bus = PublishSubject.create().toSerialized();

  public void post(Object o) {
    bus.onNext(o);
  }

  public <T> Observable<T> toObservable(Class<T> event) {
    return bus.ofType(event).onErrorReturn(new Function<Throwable, T>() {
      @Override public T apply(@NonNull Throwable throwable) throws Exception {
        return null;
      }
    }).filter(new Predicate<T>() {
      @Override public boolean test(@NonNull T t) throws Exception {
        return t != null;
      }
    }).observeOn(AndroidSchedulers.mainThread());
  }
}
