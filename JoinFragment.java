package com.teratail.q370047;

import android.os.Bundle;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class JoinFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_join, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    requireActivity().setTitle(getString(R.string.app_name) + " - 参加");

    MainViewModel vm = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    TextView contentText = view.findViewById(R.id.contentText);
    TextView placeText = view.findViewById(R.id.placeText);
    TextView dateText = view.findViewById(R.id.dateText);
    TextView timeText = view.findViewById(R.id.timeText);
    TextView numberText = view.findViewById(R.id.numberText);

    vm.getJoinSelect().observe(getViewLifecycleOwner(), data -> {
      if(data == null) {
        contentText.setText("");
        placeText.setText("");
        dateText.setText("");
        timeText.setText("");
        numberText.setText("0 人");
      } else {
        contentText.setText(data.content);
        placeText.setText(data.place);
        dateText.setText(data.date);
        timeText.setText(data.time);
        numberText.setText(data.number + " 人");
      }
    });

    Button joinButton = view.findViewById(R.id.joinButton);
    Button cancelButton = view.findViewById(R.id.cancelButton);

    joinButton.setOnClickListener(v -> {
      vm.addJoinListFromSelected();
      ((MainActivity) requireActivity()).toMainFragment();
    });

    cancelButton.setOnClickListener(v -> ((MainActivity) requireActivity()).toMainFragment());
  }
}