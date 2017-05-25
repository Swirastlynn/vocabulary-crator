package com.przemyslawlusnia.vocabularycreator.wordlist.repository

import android.util.Log
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel
import io.realm.Realm
import rx.Observable

class WordsRealmRepository(private val mapper: WordDomainMapper) : WordsRepository {

    override fun delete(words: List<WordDomainModel>): Observable<Void> {
        Realm.getDefaultInstance().use { realmInstance ->
            val wordList = arrayOfNulls<String>(words.size)
            val translationList = arrayOfNulls<String>(words.size)
            for (i in words.indices) {
                val (word, translation) = words[i]
                wordList[i] = word
                translationList[i] = translation
            }
            val result = realmInstance.where(WordRealm::class.java)
                    .`in`(WordRealm.KEY_WORD, wordList)
                    .`in`(WordRealm.KEY_TRANSLATION, translationList)
                    .findAll()
            if (result.isEmpty()) {
                Log.e(TAG, "Such word do not exists in database")
            }
            realmInstance.executeTransaction { result.deleteAllFromRealm() }

        }
        return Observable.empty<Void>()
    }

    override fun add(wordDomainModel: WordDomainModel): Observable<Boolean> {
        val wordRealm = WordRealm.mapToWordRealm(wordDomainModel)
        try {
            Realm.getDefaultInstance().use { realmInstance ->
                realmInstance.executeTransaction { realm -> realm.copyToRealmOrUpdate(wordRealm) }
            }
        } catch (e: Exception) {
            return Observable.just(false)
        }

        return Observable.just(true)
    }

    override val allWords: Observable<List<WordDomainModel>>
        get() {
            var observable = Observable.just(emptyList<WordDomainModel>())
            try {
                Realm.getDefaultInstance().use { realmInstance ->
                    val allWords = realmInstance.where(WordRealm::class.java).findAll()
                    val result = allWords.subList(0, allWords.size)
                    observable = Observable.just(mapper.mapToWordDomainModels(result))
                }
            } catch (e: Exception) {
                Log.e(TAG, "Cannot get words from database")
            }

            return observable
        }

    companion object {

        private val TAG = WordsRealmRepository::class.java.simpleName
    }
}