package com.przemyslawlusnia.vocabularycreator.core.di

import android.app.Application
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewMapper
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(app: Application)

    fun provideApplication(): Application

    fun wordViewMapper(): WordViewMapper

    fun wordDomainMapper(): WordDomainMapper

    fun wordsRealmRepository(): WordsRepository

}