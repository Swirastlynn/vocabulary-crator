package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.core.ActivitiesAndFragmentsHelper;
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity;

public class WordsActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.words_activity);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setupToolbar();
    if (savedInstanceState == null) {
      ActivitiesAndFragmentsHelper.showFragment(this, WordsFragment.newInstance(), R.id.fragmentContainer);
    }
  }

}
