package com.przemyslawlusnia.vocabularycreator.wordlist.repository

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel
import rx.Observable

interface WordsRepository {
    fun delete(words: List<WordDomainModel>): Observable<Void>

    fun add(wordDomainModel: WordDomainModel): Observable<Boolean>

    val allWords: Observable<List<WordDomainModel>>
}
