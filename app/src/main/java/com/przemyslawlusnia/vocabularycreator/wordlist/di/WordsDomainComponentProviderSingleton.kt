package com.przemyslawlusnia.vocabularycreator.wordlist.di

import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication

object WordsDomainComponentProviderSingleton {

    private var instance: WordsDomainComponent = DaggerWordsDomainComponent
            .builder()
            .appComponent(VocabularyCreatorApplication.appComponent)
            .wordsDomainModule(WordsDomainModule())
            .build()

    fun component() = instance
}