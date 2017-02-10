package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;

public class AddWordUseCase {

  private final WordsRepository wordsRepository;

  public AddWordUseCase(WordsRepository wordsRepository) {
    this.wordsRepository = wordsRepository;
  }

  public void addWord(WordRealm wordRealm) {
    wordsRepository.add(wordRealm);
  }
}
