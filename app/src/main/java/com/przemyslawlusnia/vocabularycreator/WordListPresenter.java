package com.przemyslawlusnia.vocabularycreator;

import javax.inject.Inject;

public class WordListPresenter extends PresBase<WordListView> {

  private static final String TAG = WordListPresenter.class.getSimpleName();

  @Inject
  public WordListPresenter() {

  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
