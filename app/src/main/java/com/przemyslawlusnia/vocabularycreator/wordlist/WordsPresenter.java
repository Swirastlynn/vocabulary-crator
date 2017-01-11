package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.BasePresenter;

public class WordsPresenter extends BasePresenter<WordsView> {

  private static final String TAG = WordsPresenter.class.getSimpleName();

  public WordsPresenter(WordsView wordsView) {
    this.view = wordsView;
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
