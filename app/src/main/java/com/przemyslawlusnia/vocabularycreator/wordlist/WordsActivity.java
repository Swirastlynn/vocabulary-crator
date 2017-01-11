package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.przemyslawlusnia.vocabularycreator.ActivitiesAndFragmentsHelper;
import com.przemyslawlusnia.vocabularycreator.BaseActivity;
import com.przemyslawlusnia.vocabularycreator.R;

public class WordsActivity extends BaseActivity {

  @BindView(R.id.fab)
  protected FloatingActionButton fab;

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.start_menu, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @OnClick(R.id.fab)
  public void fabClick(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show();
  }
}
