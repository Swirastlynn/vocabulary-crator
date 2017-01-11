package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.support.v7.widget.LinearLayoutManager;
import com.przemyslawlusnia.vocabularycreator.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class WordListFragmentModule {
  private final WordListActivity activity;
  private final WordListView wordListView;

  public WordListFragmentModule(WordListView wordListView, WordListActivity activity) {
    this.wordListView = wordListView;
    this.activity = activity;
  }

  @Provides
  @FragmentScope
  WordListActivity provideWordListActivity() {
    return activity;
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

  @Provides
  @FragmentScope
  WordListAdapter provideWordListAdapter() {
    return new WordListAdapter(wordListView);
  }

  @Provides
  @FragmentScope
  LinearLayoutManager provideLinearLayoutManager(WordListActivity activity) {
    return new LinearLayoutManager(activity);
  }

}