package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import android.support.v7.widget.LinearLayoutManager;
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.OnWordsSelectionListener;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsActivity;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsAdapter;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsPresenter;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsView;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;

@Module
public class WordsViewModule {
  private final WordsActivity activity;
  private final WordsView wordsView;
  private final OnWordsSelectionListener listener;

  public WordsViewModule(WordsView wordsView, OnWordsSelectionListener listener, WordsActivity activity) {
    this.wordsView = wordsView;
    this.listener = listener;
    this.activity = activity;
  }

  @Provides
  @WordsViewScope
  WordsActivity provideWordsActivity() {
    return activity;
  }

  @Provides
  @WordsViewScope
  WordsView provideWordsView() {
    return wordsView;
  }

  @Provides
  @WordsViewScope
  WordsAdapter provideWordsAdapter() {
    return new WordsAdapter(listener);
  }

  @Provides
  @WordsViewScope
  LinearLayoutManager provideLinearLayoutManager(WordsActivity activity) {
    return new LinearLayoutManager(activity);
  }

  @Provides
  @WordsViewScope
  WordsPresenter provideWordsPresenter(WordViewMapper mapper,
                                       AddWordUseCase addWordUseCase,
                                       DeleteWordUseCase deleteWordUseCase,
                                       RxUseCase<List<WordDomainModel>> getAllWordsUseCase) {
    return new WordsPresenter(
        wordsView, mapper, AndroidSchedulers.mainThread(),
        addWordUseCase, deleteWordUseCase, getAllWordsUseCase
    );
  }

}