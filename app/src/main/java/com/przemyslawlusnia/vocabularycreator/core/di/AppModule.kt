package com.przemyslawlusnia.vocabularycreator.core.di

import android.app.Application
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewMapper
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @AppScope
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @AppScope
    internal fun wordViewMapper(): WordViewMapper {
        return WordViewMapper()
    }

    @Provides
    @AppScope
    internal fun wordDomainMapper(): WordDomainMapper {
        return WordDomainMapper()
    }

    @Provides
    @AppScope
    internal fun wordsRealmRepository(wordMapper: WordDomainMapper): WordsRepository {
        return getWordsRepository(wordMapper)
    }

    private fun getWordsRepository(wordMapper: WordDomainMapper): WordsRepository {
        return WordsRealmRepository(wordMapper)
    }

}