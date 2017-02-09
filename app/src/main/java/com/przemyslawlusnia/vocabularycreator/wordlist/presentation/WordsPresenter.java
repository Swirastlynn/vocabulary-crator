package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BasePresenter;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealmRepository;
import java.util.List;

public class WordsPresenter extends BasePresenter<WordsView> {

  private static final String TAG = WordsPresenter.class.getSimpleName();

  WordRealmRepository realmRepository;

  public WordsPresenter(WordsView wordsView) { // rest of dependencies is also in constructor. Module @Provides injection :)
    this.view = wordsView;
    realmRepository = new WordRealmRepository(); // todo DI
  }

  public void addWord(ModifiableWordViewModel word) {
    realmRepository.add(WordRealm.createRealmAddress(word));
  }

  public void deleteWord(List<ModifiableWordViewModel> words) {
    realmRepository.delete(words);
  }

  @Override
  public void onUnsubscribe() {
    // not used
  }
}
