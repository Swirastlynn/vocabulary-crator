package com.przemyslawlusnia.vocabularycreator.core;

import android.util.Log;
import com.google.firebase.crash.FirebaseCrash;
import timber.log.Timber;

public class FirebaseCrashTree extends Timber.Tree {
  @Override
  protected boolean isLoggable(String tag, int priority) {
    return !(priority == Log.VERBOSE || priority == Log.INFO || priority == Log.DEBUG);
  }

  @Override
  protected void log(int priority, String tag, String message, Throwable t) {
    FirebaseCrash.logcat(priority, tag, message);
    if (t != null) {
      FirebaseCrash.report(t);
    }
  }
}
