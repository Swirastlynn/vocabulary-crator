package com.przemyslawlusnia.vocabularycreator.wordlist.domain

import com.przemyslawlusnia.vocabularycreator.core.RxUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository
import rx.Observable
import rx.Scheduler

class GetAllWordsUseCase(executionScheduler: Scheduler, private val wordsRepository: WordsRepository)
    : RxUseCase<List<WordDomainModel>>(executionScheduler) {

    public override fun buildUseCaseObservable(): Observable<List<WordDomainModel>> {
        return wordsRepository.allWords
    }
}
