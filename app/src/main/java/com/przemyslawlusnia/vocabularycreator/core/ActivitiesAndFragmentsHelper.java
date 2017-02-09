package com.przemyslawlusnia.vocabularycreator.core;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class ActivitiesAndFragmentsHelper { // todo replace with navigator, like in Cejas' example?

  private ActivitiesAndFragmentsHelper() {
  }

  public static void showFragment(BaseActivity activity, Fragment fragment, int containerId) {
    activity.getSupportFragmentManager()
        .beginTransaction()
        .replace(containerId, fragment)
        .commit();
  }

  public static void showFragmentAndAddToBackstack(BaseActivity activity, Fragment fragment, int containerId) {
    activity.getSupportFragmentManager()
        .beginTransaction()
        .addToBackStack(null)
        .replace(containerId, fragment)
        .commit();
  }

  public static void clearBackStack(AppCompatActivity activity) {
    FragmentManager manager = activity.getSupportFragmentManager();
    if (manager.getBackStackEntryCount() > 0) {
      FragmentManager.BackStackEntry first = manager.getBackStackEntryAt(0);
      manager.popBackStack(first.getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
  }

  public static void hideKeyboard(Activity activity) {
    if (activity == null) {
      return;
    }

    View view = activity.getCurrentFocus();
    if (view != null) {
      InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    activity.getWindow().setSoftInputMode(
        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
    );
  }

  public static void showKeyboard(Activity activity) {
    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
  }

}
