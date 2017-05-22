package com.przemyslawlusnia.vocabularycreator.wordlist.domain

import com.przemyslawlusnia.vocabularycreator.core.Constants

class WordDomainModel(val word: String = "",
                      val translation: String = "",
                      val type: Int = Constants.TYPE_TRAINING)