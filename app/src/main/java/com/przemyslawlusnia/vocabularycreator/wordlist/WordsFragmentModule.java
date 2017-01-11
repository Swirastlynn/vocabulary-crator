package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.support.v7.widget.LinearLayoutManager;
import com.przemyslawlusnia.vocabularycreator.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class WordsFragmentModule {
  private final WordsActivity activity;
  private final WordsView wordsView;

  public WordsFragmentModule(WordsView wordsView, WordsActivity activity) {
    this.wordsView = wordsView;
    this.activity = activity;
  }

  @Provides
  @FragmentScope
  WordsActivity provideWordsActivity() {
    return activity;
  }

  @Provides
  @FragmentScope
  WordsView provideWordsView() {
    return wordsView;
  }

  @Provides
  @FragmentScope
  WordsPresenter provideWordsPresenter() {
    return new WordsPresenter(wordsView);
  }

  @Provides
  @FragmentScope
  WordsAdapter provideWordsAdapter() {
    return new WordsAdapter(wordsView);
  }

  @Provides
  @FragmentScope
  LinearLayoutManager provideLinearLayoutManager(WordsActivity activity) {
    return new LinearLayoutManager(activity);
  }

}