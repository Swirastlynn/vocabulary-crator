package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BasePresenter;
import com.przemyslawlusnia.vocabularycreator.core.UseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import java.util.List;

public class WordsPresenter extends BasePresenter<WordsView> {

  private static final String TAG = WordsPresenter.class.getSimpleName();

  private final AddWordUseCase addWordUseCase;
  private final DeleteWordUseCase deleteWordUseCase;
  private final UseCase<List<WordRealm>> getAllWordsUseCase;

  public WordsPresenter(WordsView wordsView,
                        AddWordUseCase addWordUseCase,
                        DeleteWordUseCase deleteWordUseCase,
                        UseCase<List<WordRealm>> getAllWordsUseCase) {
    this.view = wordsView;
    this.addWordUseCase = addWordUseCase;
    this.deleteWordUseCase = deleteWordUseCase;
    this.getAllWordsUseCase = getAllWordsUseCase;
  }

  public void addWord(ModifiableWordViewModel word) {
    addWordUseCase.addWord(WordRealm.createRealmAddress(word));
  }

  public void deleteWord(List<ModifiableWordViewModel> words) {
    deleteWordUseCase.delete(words);
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
