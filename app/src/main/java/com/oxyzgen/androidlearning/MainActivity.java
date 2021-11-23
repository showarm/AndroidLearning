package com.oxyzgen.androidlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.oxyzgen.androidlearning.NeteaseDemos.InjectView.InjectView1Activity;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Button btn1 = findViewById(R.id.btn1);
    btn1.setOnClickListener((v) -> {
      Intent intent = new Intent();
      intent.setClass(this, InjectView1Activity.class);
      startActivity(intent);
    });
  }
}
