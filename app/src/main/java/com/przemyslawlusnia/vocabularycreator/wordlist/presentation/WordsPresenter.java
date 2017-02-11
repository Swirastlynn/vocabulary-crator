package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BasePresenter;
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.List;

public class WordsPresenter extends BasePresenter<WordsView> {

  private static final String TAG = WordsPresenter.class.getSimpleName();

  private final WordViewMapper mapper;
  private final AddWordUseCase addWordUseCase;
  private final DeleteWordUseCase deleteWordUseCase;
  private final RxUseCase<List<WordDomainModel>> getAllWordsUseCase;

  public WordsPresenter(WordsView wordsView,
                        WordViewMapper mapper,
                        AddWordUseCase addWordUseCase,
                        DeleteWordUseCase deleteWordUseCase,
                        RxUseCase<List<WordDomainModel>> getAllWordsUseCase) {
    this.view = wordsView;
    this.mapper = mapper;
    this.addWordUseCase = addWordUseCase;
    this.deleteWordUseCase = deleteWordUseCase;
    this.getAllWordsUseCase = getAllWordsUseCase;
  }

  public void addWord(WordViewModel word) {
    addWordUseCase.addWord(mapper.mapToWordDomainModel(word));
  }

  public void deleteWord(List<WordViewModel> words) {
    deleteWordUseCase.delete(mapper.mapToWordDomainModels(words));
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
