package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import java.util.List;

public class DeleteWordUseCase {

  private final WordsRepository wordsRepository;

  public DeleteWordUseCase(WordsRepository wordsRepository) {
    this.wordsRepository = wordsRepository;
  }

  public void delete(List<WordDomainModel> words) {
    wordsRepository.delete(words);
  }
}
