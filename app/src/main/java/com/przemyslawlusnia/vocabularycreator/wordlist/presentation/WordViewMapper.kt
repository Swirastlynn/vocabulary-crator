package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel

class WordViewMapper {

    fun mapToWordViewModel(wordDomainModel: WordDomainModel): WordViewModel {
        return WordViewModel(
                wordDomainModel.word,
                wordDomainModel.translation,
                wordDomainModel.type,
                false)
    }

    fun mapToWordViewModels(wordDomainModels: List<WordDomainModel>): List<WordViewModel> {
        return wordDomainModels.map {
            WordViewModel(
                    it.word,
                    it.translation,
                    it.type,
                    false)
        }
    }

    fun mapToWordDomainModel(wordViewModel: WordViewModel): WordDomainModel {
        return WordDomainModel(
                wordViewModel.word,
                wordViewModel.translation,
                wordViewModel.type)
    }

    fun mapToWordDomainModels(wordViewModels: List<WordViewModel>): List<WordDomainModel> {
        return wordViewModels.map { WordDomainModel(it.word, it.translation, it.type) }
    }

}
