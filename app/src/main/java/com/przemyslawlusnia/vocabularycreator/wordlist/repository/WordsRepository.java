package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.List;
import rx.Observable;

public interface WordsRepository {
  Observable<Boolean> delete(List<WordDomainModel> words);

  Observable<Boolean> add(WordDomainModel wordDomainModel);

  Observable<List<WordDomainModel>> getAllWords();
}
