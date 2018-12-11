package com.example.rxbusexample.model;

/**
 * Created by haiyu.chen on 2018/12/11.
 */

public class MessageEvent {
  private String time;
  private String message;

  public MessageEvent(String time, String message) {
    this.time = time;
    this.message = message;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
