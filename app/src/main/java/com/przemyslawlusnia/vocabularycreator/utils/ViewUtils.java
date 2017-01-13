package com.przemyslawlusnia.vocabularycreator.utils;

import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.MenuItem;

public final class ViewUtils {

  private ViewUtils() {
  }

  public static void tintMenuItemIcon(int color, MenuItem item) {
    final Drawable drawable = item.getIcon();
    if (drawable != null) {
      final Drawable wrapped = DrawableCompat.wrap(drawable);
      drawable.mutate();
      DrawableCompat.setTint(wrapped, color);
      item.setIcon(drawable);
    }
  }
}
