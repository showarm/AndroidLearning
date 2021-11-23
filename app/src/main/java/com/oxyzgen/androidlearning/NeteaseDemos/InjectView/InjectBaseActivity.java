package com.oxyzgen.androidlearning.NeteaseDemos.InjectView;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.oxyzgen.netease.InjectView.inject.InjectManager;

public class InjectBaseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    InjectManager.inject(this);
  }
}
