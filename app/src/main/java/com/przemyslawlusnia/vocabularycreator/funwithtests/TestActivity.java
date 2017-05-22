package com.przemyslawlusnia.vocabularycreator.funwithtests;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity;
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication;
import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock;
import javax.inject.Inject;

public class TestActivity extends BaseActivity {

  protected TextView testTxt;

  @Inject
  MyClock myClock;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    VocabularyCreatorApplication.getClockComponent().inject(this);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    testTxt = (TextView) findViewById(R.id.testTxt);
    setupToolbar();
    testTxt.setText(myClock.getTime());
  }

}
