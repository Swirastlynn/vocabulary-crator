package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import android.support.annotation.NonNull;
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import java.util.List;
import rx.Observable;
import rx.Scheduler;

public class DeleteWordUseCase extends RxUseCase<Void> {

  private final WordsRepository wordsRepository;
  private List<WordDomainModel> wordDomainModels;

  public DeleteWordUseCase(Scheduler executionScheduler, WordsRepository wordsRepository) {
    super(executionScheduler);
    this.wordsRepository = wordsRepository;
  }

  public DeleteWordUseCase init(@NonNull List<WordDomainModel> wordDomainModels) {
    this.wordDomainModels = wordDomainModels;
    return this;
  }

  @Override
  protected Observable<Void> buildUseCaseObservable() {
    if (this.wordDomainModels == null) {
      return Observable.create(subscriber -> {
        subscriber.onError(new Throwable("init(wordDomainModels) not called, or called with null argument."));
      });
    }
    return wordsRepository.delete(wordDomainModels);
  }

}
