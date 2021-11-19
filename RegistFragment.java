package com.teratail.q370047;

import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.widget.*;

import androidx.annotation.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class RegistFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_regist, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    requireActivity().setTitle(getString(R.string.app_name) + " - 募集登録");

    MainViewModel vm = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

    EditText contentEdit = view.findViewById(R.id.contentEdit);
    EditText placeEdit = view.findViewById(R.id.placeEdit);
    EditText dateEdit = view.findViewById(R.id.dateEdit);
    EditText timeEdit = view.findViewById(R.id.timeEdit);
    EditText numberEdit = view.findViewById(R.id.numberEdit);

    Button registButton = view.findViewById(R.id.registButton);
    Button cancelButton = view.findViewById(R.id.cancelButton);

    TextWatcher textChangeListener = new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {} //ignore
      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {} //ignore
      @Override
      public void afterTextChanged(Editable editable) {
        registButton.setEnabled(contentEdit.getText().length() > 0
                && placeEdit.getText().length() > 0
                && dateEdit.getText().length() > 0
                && timeEdit.getText().length() > 0
                && numberEdit.getText().length() > 0);
      }
    };

    contentEdit.addTextChangedListener(textChangeListener);
    placeEdit.addTextChangedListener(textChangeListener);
    dateEdit.addTextChangedListener(textChangeListener);
    timeEdit.addTextChangedListener(textChangeListener);
    numberEdit.addTextChangedListener(textChangeListener);

    registButton.setOnClickListener(v -> {
      Data data = new Data(
              contentEdit.getText().toString(),
              placeEdit.getText().toString(),
              dateEdit.getText().toString(),
              timeEdit.getText().toString(),
              numberEdit.getText().toString());

      vm.addRegistList(data);
      ((MainActivity) requireActivity()).toMainFragment();
    });

    cancelButton.setOnClickListener(v -> ((MainActivity) requireActivity()).toMainFragment());
  }
}