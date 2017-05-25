package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import com.przemyslawlusnia.vocabularycreator.core.BaseView

interface WordsView : BaseView {

    fun showAddWord(wordViewModel: WordViewModel)

    fun showAllWordsAfterDeletion()

    fun showAllWords(wordViewModels: List<WordViewModel>)
}
