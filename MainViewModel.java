package com.teratail.q370047;

import androidx.lifecycle.*;

import java.util.*;

public class MainViewModel extends ViewModel {
  private List<Data> registList = new ArrayList<>();
  private List<Data> joinList = new ArrayList<>();
  private MutableLiveData<List<Data>> registListLiveData = new MutableLiveData<>(Collections.unmodifiableList(registList));
  private MutableLiveData<List<Data>> joinListLiveData = new MutableLiveData<>(Collections.unmodifiableList(joinList));
  private MutableLiveData<Data> joinSelectLiveData = new MutableLiveData<>();

  LiveData<List<Data>> getRegistList() {
    return registListLiveData;
  }

  LiveData<List<Data>> getJoinList() {
    return joinListLiveData;
  }

  LiveData<Data> getJoinSelect() {
    return joinSelectLiveData;
  }

  void addRegistList(Data data) {
    if(data == null) throw new NullPointerException();
    registList.add(data);
    registListLiveData.setValue(registListLiveData.getValue()); //発火
  }

  boolean findJoinList(Data data) {
    return joinList.contains(data);
  }

  void setJoinSelect(Data data) {
    joinSelectLiveData.setValue(data);
  }

  void addJoinListFromSelected() {
    if(joinSelectLiveData.getValue() == null) throw new IllegalStateException();
    joinList.add(joinSelectLiveData.getValue());
    joinListLiveData.setValue(joinListLiveData.getValue()); //発火
    joinSelectLiveData.setValue(null);
  }
}