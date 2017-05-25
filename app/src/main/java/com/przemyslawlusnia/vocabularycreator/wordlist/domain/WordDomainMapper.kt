package com.przemyslawlusnia.vocabularycreator.wordlist.domain

import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm

class WordDomainMapper {

    fun mapToWordDomainModel(wordRealm: WordRealm): WordDomainModel {
        return WordDomainModel(wordRealm.word, wordRealm.translation, wordRealm.type)
    }

    fun mapToWordDomainModels(wordsRealm: List<WordRealm>): List<WordDomainModel> {
        return wordsRealm.map { WordDomainModel(it.word, it.translation, it.type) }
    }

}
