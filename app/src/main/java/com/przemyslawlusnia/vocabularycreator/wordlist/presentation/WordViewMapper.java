package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.wordlist.domain.ImmutableWordDomainModel;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.ArrayList;
import java.util.List;

public class WordViewMapper {

  public WordViewModel mapToWordViewModel(WordDomainModel wordDomainModel) {
    ModifiableWordViewModel result = ModifiableWordViewModel.create();
    result
        .setTranslation(wordDomainModel.getTranslation())
        .setWord(wordDomainModel.getWord())
        .setType(wordDomainModel.getType());
    return result;
  }

  public List<WordViewModel> mapToWordViewModels(List<WordDomainModel> wordDomainModels) {
    List<WordViewModel> result = new ArrayList<>(wordDomainModels.size());
    for (WordDomainModel word : wordDomainModels) {
      ModifiableWordViewModel viewWord = ModifiableWordViewModel.create();
      viewWord
          .setTranslation(word.getTranslation())
          .setWord(word.getWord())
          .setType(word.getType());
      result.add(viewWord);
    }
    return result;
  }


  public WordDomainModel mapToWordDomainModel(WordViewModel wordViewModel) {
    return ImmutableWordDomainModel.builder()
        .word(wordViewModel.getWord())
        .translation(wordViewModel.getTranslation())
        .type(wordViewModel.getType())
        .build();
  }

  public List<WordDomainModel> mapToWordDomainModels(List<WordViewModel> wordViewModels) {
    List<WordDomainModel> result = new ArrayList<>(wordViewModels.size());
    for (WordViewModel word : wordViewModels) {
      WordDomainModel viewWord = ImmutableWordDomainModel.builder()
          .word(word.getWord())
          .translation(word.getTranslation())
          .type(word.getType())
          .build();
      result.add(viewWord);
    }
    return result;
  }

}
