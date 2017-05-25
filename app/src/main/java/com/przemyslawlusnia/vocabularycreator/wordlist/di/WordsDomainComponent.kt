package com.przemyslawlusnia.vocabularycreator.wordlist.di

import com.przemyslawlusnia.vocabularycreator.core.di.AppComponent
import dagger.Component

/**
 * If I had this Component kept in reference in Application class,
 * then I would be responsible for its lifecycle -> it should be released: wordsDomainComponent = null
 * Check: http://frogermcs.github.io/dependency-injection-with-dagger-2-custom-scopes/
 *
 *
 * In such case I should use custom scope.
 */
@WordsDomainScope
@Component(modules = arrayOf(WordsDomainModule::class), dependencies = arrayOf(AppComponent::class))
interface WordsDomainComponent {

    fun plus(wordsViewModule: WordsViewModule): WordsViewComponent

}