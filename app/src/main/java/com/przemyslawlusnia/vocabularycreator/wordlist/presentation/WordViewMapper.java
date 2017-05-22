package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordViewMapper {

  public WordViewModel mapToWordViewModel(WordDomainModel wordDomainModel) {
    WordViewModel result = new WordViewModel();
    result.setTranslation(wordDomainModel.getTranslation());
    result.setWord(wordDomainModel.getWord());
    result.setType(wordDomainModel.getType());
    return result;
  }

  public List<WordViewModel> mapToWordViewModels(List<WordDomainModel> wordDomainModels) {
    if (wordDomainModels == null) {
      return Collections.emptyList();
    }
    List<WordViewModel> result = new ArrayList<>(wordDomainModels.size());
    for (WordDomainModel word : wordDomainModels) {
      WordViewModel viewWord = new WordViewModel();
      viewWord.setTranslation(word.getTranslation());
      viewWord.setWord(word.getWord());
      viewWord.setType(word.getType());
      result.add(viewWord);
    }
    return result;
  }

  public WordDomainModel mapToWordDomainModel(WordViewModel wordViewModel) {
    return new WordDomainModel(wordViewModel.getWord(), wordViewModel.getTranslation(), wordViewModel.getType());
  }

  public List<WordDomainModel> mapToWordDomainModels(List<WordViewModel> wordViewModels) {
    if (wordViewModels == null) {
      return Collections.emptyList();
    }
    List<WordDomainModel> result = new ArrayList<>(wordViewModels.size());
    for (WordViewModel word : wordViewModels) {
      result.add(new WordDomainModel(word.getWord(), word.getTranslation(), word.getType()));
    }
    return result;
  }

}
