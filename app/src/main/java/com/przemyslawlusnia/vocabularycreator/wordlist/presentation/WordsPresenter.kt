package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import com.przemyslawlusnia.vocabularycreator.core.BasePresenter
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel
import rx.Observer
import rx.subscriptions.Subscriptions

class WordsPresenter(private val mapper: WordViewMapper,
                     private val uiScheduler: rx.Scheduler,
                     private val addWordUseCase: AddWordUseCase,
                     private val deleteWordUseCase: DeleteWordUseCase,
                     private val getAllWordsUseCase: RxUseCase<List<WordDomainModel>>) : BasePresenter<WordsView>() {
    private var addWordSubscription = Subscriptions.empty()
    private var deleteWordSubscription = Subscriptions.empty()
    private var getAllWordsSubscription = Subscriptions.empty()

    fun addWord(wordViewModel: WordViewModel) {
        view?.showProgress()
        addWordUseCase.init(mapper.mapToWordDomainModel(wordViewModel))
        addWordSubscription = addWordUseCase.execute()
                .observeOn(uiScheduler)
                .subscribe(AddWordObserver(wordViewModel))
    }

    fun deleteWords(wordViewModels: List<WordViewModel>) {
        view?.showProgress()
        deleteWordUseCase.init(mapper.mapToWordDomainModels(wordViewModels))
        deleteWordSubscription = deleteWordUseCase.execute()
                .observeOn(uiScheduler)
                .subscribe(DeleteWordObserver())
    }

    fun getAllWords() {
        view?.showProgress()
        getAllWordsSubscription = getAllWordsUseCase.execute()
                .map { wordDomainModels -> mapper.mapToWordViewModels(wordDomainModels) }
                .observeOn(uiScheduler)
                .subscribe(GetAllWordsObserver())
    }

    override fun onUnsubscribe() {
        if (!deleteWordSubscription.isUnsubscribed) {
            deleteWordSubscription.unsubscribe()
        }
        if (!getAllWordsSubscription.isUnsubscribed) {
            getAllWordsSubscription.unsubscribe()
        }
        if (!addWordSubscription.isUnsubscribed) {
            addWordSubscription.unsubscribe()
        }
    }

    // Observers

    private inner class AddWordObserver(private val wordViewModel: WordViewModel) : Observer<Boolean> {

        override fun onCompleted() {
            view?.hideProgress()
        }

        override fun onError(t: Throwable) {
            view?.hideProgress()
            view?.showFailure(t.message)
        }

        override fun onNext(success: Boolean?) {
            if (success!!) {
                view?.showAddWord(wordViewModel)
            }
        }
    }

    private inner class GetAllWordsObserver : Observer<List<WordViewModel>> {

        override fun onCompleted() {
            view?.hideProgress()
        }

        override fun onError(t: Throwable) {
            view?.hideProgress()
            view?.showFailure(t.message)
        }

        override fun onNext(wordViewModels: List<WordViewModel>) {
            view?.showAllWords(wordViewModels)
        }

    }

    private inner class DeleteWordObserver : Observer<Void> {

        override fun onCompleted() {
            view?.showAllWordsAfterDeletion()
            view?.hideProgress()
        }

        override fun onError(t: Throwable) {
            view?.hideProgress()
            view?.showFailure(t.message)
        }

        override fun onNext(aVoid: Void) {
            // nothing
        }
    }

}
