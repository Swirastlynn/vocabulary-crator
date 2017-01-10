package com.przemyslawlusnia.vocabularycreator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import javax.annotation.Nullable;

public abstract class BaseActivity extends AppCompatActivity {

  @Nullable
  @BindView(R.id.toolbar)
  protected Toolbar toolbar;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setSupportActionBar(toolbar);
  }
}
