package com.przemyslawlusnia.vocabularycreator;

public interface BaseView {
  void showProgress();

  void hideProgress();

  void showFailure(Throwable t);
}
