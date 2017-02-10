package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.ModifiableWordViewModel;
import java.util.List;
import rx.Observable;

public interface WordsRepository {
  void delete(List<ModifiableWordViewModel> words);

  void add(WordRealm wordRealm);

  Observable<List<WordRealm>> getAllWords();
}
