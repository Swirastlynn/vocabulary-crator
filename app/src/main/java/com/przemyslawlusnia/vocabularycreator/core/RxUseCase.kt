package com.przemyslawlusnia.vocabularycreator.core

import rx.Observable
import rx.Scheduler

abstract class RxUseCase<T>(private val executionScheduler: Scheduler) {

    protected abstract fun buildUseCaseObservable(): Observable<T>

    fun execute(): Observable<T> {
        return buildUseCaseObservable().subscribeOn(executionScheduler)
    }
}
