package com.przemyslawlusnia.vocabularycreator.wordlist.di

import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsFragment
import dagger.Subcomponent

@WordsViewScope
@Subcomponent(modules = arrayOf(WordsViewModule::class))
interface WordsViewComponent {

    fun inject(wordsFragment: WordsFragment)

}