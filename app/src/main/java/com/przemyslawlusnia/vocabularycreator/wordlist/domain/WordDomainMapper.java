package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import java.util.ArrayList;
import java.util.List;

public class WordDomainMapper {

  public WordDomainModel mapToWordDomainModel(WordRealm wordRealm) {
    return ImmutableWordDomainModel.builder()
        .word(wordRealm.getWord())
        .translation(wordRealm.getTranslation())
        .type(wordRealm.getType())
        .build();
  }

  public List<WordDomainModel> mapToWordDomainModels(List<WordRealm> wordsRealm) {
    List<WordDomainModel> result = new ArrayList<>(wordsRealm.size());
    for (WordRealm wordRealm : wordsRealm) {
      result.add(ImmutableWordDomainModel.builder()
          .word(wordRealm.getWord())
          .translation(wordRealm.getTranslation())
          .type(wordRealm.getType())
          .build());
    }
    return result;
  }

}
