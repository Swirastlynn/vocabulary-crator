package com.przemyslawlusnia.vocabularycreator;

import android.support.annotation.ColorInt;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import com.przemyslawlusnia.vocabularycreator.utils.ObjectUtils;
import javax.annotation.Nullable;

public abstract class BaseActivity extends AppCompatActivity {

  private static final String TAG = BaseActivity.class.getSimpleName();

  @Nullable
  @BindView(R.id.toolbar)
  protected Toolbar toolbar;

  protected void setupToolbar() {
    if (ObjectUtils.isNotNull(TAG, "setupToolbar", toolbar)) {
      setSupportActionBar(toolbar);
      setToolbarTitle(getString(R.string.app_name));
    }
  }

  protected void setToolbarTitle(CharSequence title) {
    if (ObjectUtils.isNotNull(TAG, "setToolbarTitle", toolbar)) {
      toolbar.setTitle(title);
    }
  }

  protected void setToolbarColor(@ColorInt int color) {
    if (ObjectUtils.isNotNull(TAG, "setToolbarColor", toolbar)) {
      toolbar.setBackgroundColor(color);
    }
  }
}
