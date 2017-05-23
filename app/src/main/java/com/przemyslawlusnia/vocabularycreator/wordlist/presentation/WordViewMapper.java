package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordViewMapper {

  public WordViewModel mapToWordViewModel(WordDomainModel wordDomainModel) {
    return new WordViewModel(
        wordDomainModel.getWord(),
        wordDomainModel.getTranslation(),
        wordDomainModel.getType(),
        false);
  }

  public List<WordViewModel> mapToWordViewModels(List<WordDomainModel> wordDomainModels) {
    if (wordDomainModels == null) {
      return Collections.emptyList();
    }
    List<WordViewModel> result = new ArrayList<>(wordDomainModels.size());
    for (WordDomainModel word : wordDomainModels) {
      WordViewModel viewWord = new WordViewModel(
          word.getWord(),
          word.getTranslation(),
          word.getType(),
          false);
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
