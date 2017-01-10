package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class WordListFragmentModule {
  private WordListView wordListView;

  public WordListFragmentModule(WordListView wordListView) {
    this.wordListView = wordListView;
  }

  @Provides
  @FragmentScope
  WordListView provideWordListView() {
    return wordListView;
  }

  @Provides
  @FragmentScope
  WordListPresenter provideWordListPresenter() {
    return new WordListPresenter(wordListView);
  }
}