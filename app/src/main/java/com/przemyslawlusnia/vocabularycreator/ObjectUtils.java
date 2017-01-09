package com.przemyslawlusnia.vocabularycreator;

import android.util.Log;
import java.util.Collections;
import java.util.List;

public final class ObjectUtils {

  private static final String TAG = ObjectUtils.class.getSimpleName();

  private ObjectUtils() {
  }

  public static <T> List<T> safeList(List<T> other) {
    return other == null ? Collections.EMPTY_LIST : other;
  }

  public static <T> boolean isNotNull(String tag, String function, T other) {
    boolean result = other != null;
    if (!result) {
      Log.e(tag, "null in " + function);
    }
    return result;
  }
}
