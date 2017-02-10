package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;

public class AddWordUseCase {

  private final WordsRealmRepository wordsRealmRepository;

  public AddWordUseCase(WordsRealmRepository wordsRealmRepository) {
    this.wordsRealmRepository = wordsRealmRepository;
  }

  public void addWord(WordRealm wordRealm) {
    wordsRealmRepository.add(wordRealm);
  }
}
