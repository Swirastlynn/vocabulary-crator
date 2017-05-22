package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import com.przemyslawlusnia.vocabularycreator.core.Constants

class WordViewModel { // todo initialization

    var word: String = ""
        get() = ""

    var translation: String = ""
        get() = ""

    var type: Int = Constants.TYPE_TRAINING
        get() = Constants.TYPE_TRAINING

    var isSelected: Boolean = false
        get() = false
}