package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import com.przemyslawlusnia.vocabularycreator.core.Constants

data class WordViewModel(var word: String,
                    var translation: String,
                    var type: Int = Constants.TYPE_TRAINING,
                    var isSelected: Boolean = false) // todo default values