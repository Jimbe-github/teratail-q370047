package com.teratail.q370047;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //MainViewModel vm = new ViewModelProvider(this).get(MainViewModel.class);

    toMainFragment();
  }

  void toMainFragment() {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_container, new MainFragment())
            .commit();
  }

  void toRegistFragment() {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_container, new RegistFragment())
            .commit();
  }

  void toJoinFragment() {
    getSupportFragmentManager().beginTransaction()
            .replace(R.id.main_container, new JoinFragment())
            .commit();
  }
}