package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.core.AbstractUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import java.util.List;
import rx.Observable;
import rx.Scheduler;

public class GetAllWordsUseCase extends AbstractUseCase<List<WordDomainModel>> {

  private WordsRepository wordsRepository;

  public GetAllWordsUseCase(Scheduler executionScheduler, WordsRepository wordsRepository) {
    super(executionScheduler);
    this.wordsRepository = wordsRepository;
  }

  @Override
  protected Observable<List<WordDomainModel>> emit() {
    return wordsRepository.getAllWords();
  }
}
