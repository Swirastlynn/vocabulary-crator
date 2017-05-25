package com.przemyslawlusnia.vocabularycreator.wordlist.di

import android.support.v7.widget.LinearLayoutManager
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.*
import dagger.Module
import dagger.Provides
import rx.android.schedulers.AndroidSchedulers

@Module
class WordsViewModule(private val wordsView: WordsView,
                      private val listener: OnWordsSelectionListener,
                      private val activity: WordsActivity) {

    @Provides
    @WordsViewScope
    internal fun provideWordsActivity(): WordsActivity {
        return activity
    }

    @Provides
    @WordsViewScope
    internal fun provideWordsView(): WordsView {
        return wordsView
    }

    @Provides
    @WordsViewScope
    internal fun provideWordsAdapter(): WordsAdapter {
        return WordsAdapter(listener)
    }

    @Provides
    @WordsViewScope
    internal fun provideLinearLayoutManager(activity: WordsActivity): LinearLayoutManager {
        return LinearLayoutManager(activity)
    }

    @Provides
    @WordsViewScope
    internal fun provideWordsPresenter(mapper: WordViewMapper,
                                       addWordUseCase: AddWordUseCase,
                                       deleteWordUseCase: DeleteWordUseCase,
                                       getAllWordsUseCase: RxUseCase<List<WordDomainModel>>): WordsPresenter {
        return WordsPresenter(
                wordsView, mapper, AndroidSchedulers.mainThread(),
                addWordUseCase, deleteWordUseCase, getAllWordsUseCase
        )
    }

}