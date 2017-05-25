package com.przemyslawlusnia.vocabularycreator.wordlist.domain

import com.przemyslawlusnia.vocabularycreator.core.RxUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository
import rx.Observable
import rx.Scheduler

class AddWordUseCase(executionScheduler: Scheduler, private val wordsRepository: WordsRepository)
    : RxUseCase<Boolean>(executionScheduler) {

    lateinit private var wordDomainModel: WordDomainModel

    fun initialize(wordDomainModel: WordDomainModel) {
        this.wordDomainModel = wordDomainModel
    }

    public override fun buildUseCaseObservable(): Observable<Boolean> {
        return Observable.concat(validate(), wordsRepository.add(wordDomainModel))
    }

    private fun validate(): Observable<Boolean> {
        return Observable.create<Boolean> { subscriber ->
            if (wordDomainModel.word.isEmpty()) {
                subscriber.onError(Throwable("Error: word cannot be empty."))
            } else {
                subscriber.onCompleted()
            }
        }
    }
}
