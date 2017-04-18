package com.przemyslawlusnia.vocabularycreator.funwithtests;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity;
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication;
import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock;
import javax.inject.Inject;

public class TestActivity extends BaseActivity {

  @BindView(R.id.testTxt)
  protected TextView testTxt;

  @Inject
  MyClock myClock;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    VocabularyCreatorApplication.getClockComponent().inject(this);
    ButterKnife.bind(this);
    setupToolbar();
    testTxt.setText(myClock.getTime());
  }

}
