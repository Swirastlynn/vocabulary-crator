package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.List;
import rx.Observable;

public interface WordsRepository {
  void delete(List<WordDomainModel> words);

  void add(WordDomainModel wordRealm);

  Observable<List<WordDomainModel>> getAllWords();
}
