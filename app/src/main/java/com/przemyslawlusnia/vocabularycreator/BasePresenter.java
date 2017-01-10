package com.przemyslawlusnia.vocabularycreator;

public abstract class BasePresenter<V> {

  public V view;

  public void onAttachView(V frag) {
    view = frag;
  }

  public void onDestroy() {
    view = null;
  }

  public abstract void onUnsubscribe();

}
