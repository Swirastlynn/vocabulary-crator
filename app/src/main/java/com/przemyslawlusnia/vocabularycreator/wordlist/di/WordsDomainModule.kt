package com.przemyslawlusnia.vocabularycreator.wordlist.di

import com.przemyslawlusnia.vocabularycreator.core.RxUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.GetAllWordsUseCase
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository
import dagger.Module
import dagger.Provides
import rx.schedulers.Schedulers

@Module
class WordsDomainModule {

    @Provides
    @WordsDomainScope
    internal fun addWordUseCase(wordsRepository: WordsRepository): AddWordUseCase {
        return AddWordUseCase(Schedulers.io(), wordsRepository)
    }

    @Provides
    @WordsDomainScope
    internal fun deleteWordUseCase(wordsRepository: WordsRepository): DeleteWordUseCase {
        return DeleteWordUseCase(Schedulers.io(), wordsRepository)
    }

    @Provides
    @WordsDomainScope
    internal fun getAllWordsUseCase(wordsRepository: WordsRepository): RxUseCase<List<WordDomainModel>> {
        return GetAllWordsUseCase(Schedulers.io(), wordsRepository)
    }

}