package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.PresBase;

public class WordListPresenter extends PresBase<WordListView> {

  private static final String TAG = WordListPresenter.class.getSimpleName();

  public WordListPresenter(WordListView wordListView) {
    this.view = wordListView;
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
