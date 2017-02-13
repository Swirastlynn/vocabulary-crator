package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import android.support.annotation.NonNull;
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import rx.Observable;
import rx.Scheduler;

public class AddWordUseCase extends RxUseCase<Boolean> {

  private WordDomainModel wordDomainModel;

  private final WordsRepository wordsRepository;

  public AddWordUseCase(Scheduler executionScheduler, WordsRepository wordsRepository) {
    super(executionScheduler);
    this.wordsRepository = wordsRepository;
  }

  public AddWordUseCase init(@NonNull WordDomainModel wordDomainModel) {
    this.wordDomainModel = wordDomainModel;
    return this;
  }

  @Override
  protected Observable<Boolean> buildUseCaseObservable() {
    if (this.wordDomainModel == null) {
      throw new IllegalArgumentException("init(wordDomainModel) not called, or called with null argument.");
    }
    return Observable.concat(validate(), wordsRepository.add(wordDomainModel));
  }

  private Observable<Boolean> validate() {
    return Observable.create(subscriber -> {
      if (AddWordUseCase.this.wordDomainModel.getWord().isEmpty()) {
        subscriber.onError(new Throwable("Error: word cannot be empty."));
      } else {
        subscriber.onCompleted();
      }
    });
  }
}
