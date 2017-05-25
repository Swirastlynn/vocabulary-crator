package com.przemyslawlusnia.vocabularycreator.wordlist.domain

import com.przemyslawlusnia.vocabularycreator.core.RxUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository
import rx.Observable
import rx.Scheduler

class DeleteWordUseCase(executionScheduler: Scheduler, private val wordsRepository: WordsRepository)
    : RxUseCase<Void>(executionScheduler) {

    lateinit private var wordDomainModels: List<WordDomainModel>

    fun initialize(wordDomainModels: List<WordDomainModel>) {
        this.wordDomainModels = wordDomainModels
    }

    public override fun buildUseCaseObservable(): Observable<Void> {
        return wordsRepository.delete(wordDomainModels)
    }

}
