package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BasePresenter;

public class WordsPresenter extends BasePresenter<WordsView> {

  private static final String TAG = WordsPresenter.class.getSimpleName();

  public WordsPresenter(WordsView wordsView) { // rest of dependencies is also in constructor. Module @Provides injection :)
    this.view = wordsView;
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
