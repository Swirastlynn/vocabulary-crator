package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;

public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {

  // XML usage needs constructor with context and attrs
  public ScrollAwareFABBehavior(Context context, AttributeSet attrs) {
    super();
  }

  @Override
  public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                                     View directTargetChild, View target, int nestedScrollAxes) {
    return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
  }

  @Override
  public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child,
                             View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
    super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);

    // fix of 25.1.0 library version "intended bug": https://code.google.com/p/android/issues/detail?id=230298
    final FloatingActionButton.OnVisibilityChangedListener visibilityChangedListener
        = new FloatingActionButton.OnVisibilityChangedListener() {
      @Override
      public void onHidden(FloatingActionButton fab) {
        super.onShown(fab);
        fab.setVisibility(View.INVISIBLE);
      }
    };
    if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
      child.hide(visibilityChangedListener);
    } else if (dyConsumed < 0 && child.getVisibility() == View.VISIBLE) {
      child.hide(visibilityChangedListener);
    }
  }

  @Override
  public void onStopNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target) {
    child.show();
    super.onStopNestedScroll(coordinatorLayout, child, target);
  }
}
