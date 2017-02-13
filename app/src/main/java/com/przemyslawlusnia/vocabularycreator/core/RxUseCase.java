package com.przemyslawlusnia.vocabularycreator.core;

import rx.Observable;
import rx.Scheduler;

public abstract class RxUseCase<T> {

  private final Scheduler executionScheduler;

  public RxUseCase(Scheduler executionScheduler) {
    this.executionScheduler = executionScheduler;
  }

  protected abstract Observable<T> buildUseCaseObservable();

  public Observable<T> execute() {
    return buildUseCaseObservable().subscribeOn(executionScheduler);
  }
}
