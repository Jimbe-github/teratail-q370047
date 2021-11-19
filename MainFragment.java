package com.teratail.q370047;

import android.os.Bundle;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.*;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.*;

public class MainFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    requireActivity().setTitle(R.string.app_name);

    LifecycleOwner lifecycleOwner = getViewLifecycleOwner();
    MainViewModel vm = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    ListView registList = view.findViewById(R.id.registList);
    Button registButton = view.findViewById(R.id.registButton);
    ListView joinList = view.findViewById(R.id.joinList);

    vm.getRegistList().observe(lifecycleOwner, list -> registList.setAdapter(new DataAdapter(list)));
    registList.setOnItemClickListener((parent, v, position, id) -> {
      Data data = (Data)parent.getItemAtPosition(position);
      if(vm.findJoinList(data)) {
        Toast.makeText(getContext(), "参加済み", Toast.LENGTH_SHORT).show();
      } else {
        vm.setJoinSelect(data);
        ((MainActivity) requireActivity()).toJoinFragment();
      }
    });

    registButton.setOnClickListener(v -> ((MainActivity)requireActivity()).toRegistFragment());

    vm.getJoinList().observe(lifecycleOwner, list -> joinList.setAdapter(new DataAdapter(list)));
  }
}

class DataAdapter extends BaseAdapter {
  private List<Data> dataList;

  DataAdapter(List<Data> dataList) {
    this.dataList = new ArrayList<>(dataList); //コピー
  }
  @Override
  public int getCount() {
    return dataList.size();
  }
  @Override
  public Object getItem(int i) {
    return dataList.get(i);
  }
  @Override
  public long getItemId(int i) {
    return i;
  }

  class ViewHolder {
    final TextView contentText;
    final TextView placeText;
    final TextView datetimeText;
    final TextView numberText;
    ViewHolder(View view) {
      contentText = view.findViewById(R.id.contentText);
      placeText = view.findViewById(R.id.placeText);
      datetimeText = view.findViewById(R.id.datetimeText);
      numberText = view.findViewById(R.id.numberText);
    }
    void setData(Data data) {
      contentText.setText(data.content);
      placeText.setText(data.place);
      datetimeText.setText(data.date + " " + data.time);
      numberText.setText(data.number + "人");
    }
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    ViewHolder vh = null;
    if(view == null){
      view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.data_row_layout, null);
      vh = new ViewHolder(view);
      view.setTag(vh);
    } else{
      vh = (ViewHolder)view.getTag();
    }

    vh.setData(dataList.get(i));

    return view;
  }
}