package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import android.support.v7.widget.LinearLayoutManager;
import com.przemyslawlusnia.vocabularycreator.core.UseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsActivity;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsAdapter;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsPresenter;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsView;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import dagger.Module;
import dagger.Provides;
import java.util.List;

@Module
public class WordsViewModule {
  private final WordsActivity activity;
  private final WordsView wordsView;

  public WordsViewModule(WordsView wordsView, WordsActivity activity) {
    this.wordsView = wordsView;
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
    return new WordsAdapter(wordsView);
  }

  @Provides
  @WordsViewScope
  LinearLayoutManager provideLinearLayoutManager(WordsActivity activity) {
    return new LinearLayoutManager(activity);
  }

  @Provides
  @WordsViewScope
  WordsPresenter provideWordsPresenter(AddWordUseCase addWordUseCase,
                                       DeleteWordUseCase deleteWordUseCase,
                                       UseCase<List<WordRealm>> getAllWordsUseCase) {
    return new WordsPresenter(wordsView, addWordUseCase, deleteWordUseCase, getAllWordsUseCase);
  }

}