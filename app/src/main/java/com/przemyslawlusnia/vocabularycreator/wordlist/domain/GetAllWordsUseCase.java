package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.core.AbstractUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;
import java.util.List;
import rx.Observable;
import rx.Scheduler;

public class GetAllWordsUseCase extends AbstractUseCase<List<WordRealm>> {

  private WordsRealmRepository wordsRealmRepository;

  public GetAllWordsUseCase(Scheduler executionScheduler, WordsRealmRepository wordsRealmRepository) {
    super(executionScheduler);
    this.wordsRealmRepository = wordsRealmRepository;
  }

  @Override
  protected Observable<List<WordRealm>> emit() {
    return wordsRealmRepository.getAllWords();
  }
}
