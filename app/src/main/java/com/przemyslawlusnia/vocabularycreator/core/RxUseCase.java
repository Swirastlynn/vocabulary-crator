package com.przemyslawlusnia.vocabularycreator.core;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public abstract class RxUseCase<T> {

  private final Scheduler executionScheduler;
  private Subscription subscription = Subscriptions.empty();
  // todo serve subscription (with unsubscription) here, add observeOn scheduler

  public RxUseCase(Scheduler executionScheduler) {
    this.executionScheduler = executionScheduler;
  }

  protected abstract Observable<T> buildUseCaseObservable();

  public Observable<T> execute() {
    return buildUseCaseObservable().subscribeOn(executionScheduler);
  }
  // todo put creation of Observable here if possible (mapping can block possibility)
}
