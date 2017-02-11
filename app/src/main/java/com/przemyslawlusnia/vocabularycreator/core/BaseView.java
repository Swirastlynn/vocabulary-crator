package com.przemyslawlusnia.vocabularycreator.core;

public interface BaseView {
  void showProgress();

  void hideProgress();

  void showFailure(String message);
}
