package com.teratail.q370047;

import java.util.*;

class Data {
  final String content; //内容
  final String place; //場所
  final String date; //日付
  final String time; //時間
  final String number; //人数

  Data(String content, String place, String date, String time, String number) {
    if(content == null || place == null || date == null || time == null || number == null) throw new NullPointerException();
    if(content.isEmpty() || place.isEmpty() || date.isEmpty() || time.isEmpty() || number.isEmpty()) throw new IllegalArgumentException();
    this.content = content;
    this.place = place;
    this.date = date;
    this.time = time;
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if(this == o)
      return true;
    if(o == null || getClass() != o.getClass())
      return false;
    Data data = (Data) o;
    return content.equals(data.content) && place.equals(data.place) && date.equals(data.date) && time.equals(data.time) && number.equals(data.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, place, date, time, number);
  }

  @Override
  public String toString() {
    StringJoiner sj = new StringJoiner(", ", "{", "}");
    sj.add("content='" + content + "'");
    sj.add("place='" + place + "'");
    sj.add("date='" + date + "'");
    sj.add("time='" + time + "'");
    sj.add("number='" + number + "'");
    return super.toString() + sj.toString();
  }
}