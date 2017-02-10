package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication;

public enum WordsDomainComponentProvider {

  INSTANCE;

  private WordsDomainComponent wordsDomainComponent;

  public WordsDomainComponent getWordsDomainComponent() {
    if (wordsDomainComponent == null) {
      wordsDomainComponent = DaggerWordsDomainComponent
          .builder()
          .appComponent(VocabularyCreatorApplication.getAppComponent())
          .wordsDomainModule(new WordsDomainModule())
          .build();
    }
    return wordsDomainComponent;
  }
}
