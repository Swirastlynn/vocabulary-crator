package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.ModifiableWordViewModel;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;
import java.util.List;

public class DeleteWordUseCase {

  private final WordsRealmRepository wordsRealmRepository;

  public DeleteWordUseCase(WordsRealmRepository wordsRealmRepository) {
    this.wordsRealmRepository = wordsRealmRepository;
  }

  public void delete(List<ModifiableWordViewModel> words) {
    wordsRealmRepository.delete(words);
  }
}
