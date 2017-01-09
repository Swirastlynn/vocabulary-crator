package com.przemyslawlusnia.vocabularycreator;

public interface ViewBase {
  void showProgress();

  void hideProgress();

  void showFailure(Throwable t);
}
