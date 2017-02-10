package com.przemyslawlusnia.vocabularycreator.core;

import rx.Observable;
import rx.Scheduler;

public abstract class AbstractUseCase<T> implements UseCase<T> { // todo rename to rx use case

    private final Scheduler executionScheduler;

    public AbstractUseCase(Scheduler executionScheduler) {
        this.executionScheduler = executionScheduler;
    }

    protected abstract Observable<T> emit();

    @Override
    public Observable<T> execute() {
        return emit().subscribeOn(executionScheduler);
    }
}
