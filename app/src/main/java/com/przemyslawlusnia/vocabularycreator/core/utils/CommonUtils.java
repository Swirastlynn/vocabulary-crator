package com.przemyslawlusnia.vocabularycreator.core.utils;

import com.przemyslawlusnia.vocabularycreator.BuildConfig;
import java.util.Map;

public class CommonUtils {

  public static boolean isEmptyText(String text) {
    return text == null || text.isEmpty() || text.equalsIgnoreCase("null");
  }

  public static void putNonNullObjectToMap(String key, Object value, Map<String, Object> requestBody) {
    if (value != null && !isEmptyText(key)) {
      requestBody.put(key, value);
    }
  }

  public static boolean isDebug() {
    return BuildConfig.DEBUG && BuildConfig.BUILD_TYPE.equals("debug");
  }

  public static boolean isDevDebug() {
    return BuildConfig.DEBUG && BuildConfig.FLAVOR.equals("dev") && BuildConfig.BUILD_TYPE.equals("debug");
  }

  public static boolean isProd() {
    return BuildConfig.FLAVOR.equals("prod");
  }

}
