package com.przemyslawlusnia.vocabularycreator.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import butterknife.Unbinder;
import com.przemyslawlusnia.vocabularycreator.core.utils.ObjectUtils;

public class BaseFragment extends Fragment {

  private static final String TAG = BaseFragment.class.getSimpleName();

  /**
   * There are two types of requests: on demand (click) and on enter screen.
   * <p>
   * On demand: use {@link BaseFragment#networking} to keep info if request has started.
   * If config changes (screen rotation, language change, keyboard change etc...) you will be able to retrieve it
   * with savedInstanceState and restart from onResume() if mentioned value is true.
   * <p>
   * On enter screen: call request from onCreate or onResume (which you prefer for your case)
   */
  protected boolean networking = false;
  protected static final String NETWORKING_STATE = "NETWORKING_STATE";

  protected Unbinder butterknifeUnbinder;
  protected EventListener eventListener;

  public BaseFragment() {
    // not used
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof EventListener) {
      eventListener = (EventListener) context;
    } else {
      Log.e(TAG, "onAttach, context is not instanceof EventListener");
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
      networking = savedInstanceState.getBoolean(NETWORKING_STATE);
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    outState.putBoolean(NETWORKING_STATE, networking);
  }

  protected void setToolbarTitle(String txt) {
    if (ObjectUtils.isNotNull("setToolbarTitle", TAG, eventListener)) {
      eventListener.setToolbarTitleTxt(txt);
    }
  }

  protected void setToolbarBackground(@ColorRes int colorRes) {
    if (ObjectUtils.isNotNull("setToolbarBackground", TAG, eventListener)) {
      eventListener.setToolbarBackground(colorRes);
    }
  }

  protected void setToolbarShadow(boolean visible) {
    if (ObjectUtils.isNotNull("setToolbarShadow", TAG, eventListener)) {
      eventListener.setToolbarShadow(visible);
    }
  }

  protected void checkListenerClass(Context context, Class<?> expectedContextClass) {
    if (!expectedContextClass.isAssignableFrom(context.getClass())) {
      throw new RuntimeException(context.toString() + " must implement " + expectedContextClass.getSimpleName());
    }
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    if (butterknifeUnbinder != null) {
      butterknifeUnbinder.unbind();
    }
  }

  @Override
  public void onDetach() {
    eventListener = null;
    super.onDetach();
  }

  interface EventListener {
    void setToolbarTitleTxt(String txt);

    void setToolbarBackground(@ColorRes int colorRes);

    void setToolbarShadow(boolean visible);
  }

}
