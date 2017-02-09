package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import android.support.v7.widget.LinearLayoutManager;
import com.przemyslawlusnia.vocabularycreator.core.di.FragmentScope;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsActivity;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsAdapter;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsPresenter;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsView;
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