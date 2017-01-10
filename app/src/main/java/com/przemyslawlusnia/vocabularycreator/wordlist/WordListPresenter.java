package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.BasePresenter;

public class WordListPresenter extends BasePresenter<WordListView> {

  private static final String TAG = WordListPresenter.class.getSimpleName();

  public WordListPresenter(WordListView wordListView) {
    this.view = wordListView;
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
