package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

interface WordDialogListener {
    fun positiveButtonClick(newWord: WordViewModel)

    fun negativeButtonClick()
}