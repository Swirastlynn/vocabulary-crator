package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WordDomainMapper {

  public WordDomainModel mapToWordDomainModel(WordRealm wordRealm) {
    return new WordDomainModel(wordRealm.getWord(), wordRealm.getTranslation(), wordRealm.getType());
  }

  public List<WordDomainModel> mapToWordDomainModels(List<WordRealm> wordsRealm) {
    if (wordsRealm == null) {
      return Collections.emptyList();
    }
    List<WordDomainModel> result = new ArrayList<>(wordsRealm.size());
    for (WordRealm wordRealm : wordsRealm) {
      result.add(new WordDomainModel(wordRealm.getWord(), wordRealm.getTranslation(), wordRealm.getType()));
    }
    return result;
  }

}
