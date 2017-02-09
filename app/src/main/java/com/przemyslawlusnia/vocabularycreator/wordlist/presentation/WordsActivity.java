package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.os.Bundle;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.core.ActivitiesAndFragmentsHelper;
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity;
import com.przemyslawlusnia.vocabularycreator.R;

public class WordsActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.words_activity);
    ButterKnife.bind(this);
    setupToolbar();
    if (savedInstanceState == null) {
      ActivitiesAndFragmentsHelper.showFragment(this, WordsFragment.newInstance(), R.id.fragmentContainer);
    }
  }

}
