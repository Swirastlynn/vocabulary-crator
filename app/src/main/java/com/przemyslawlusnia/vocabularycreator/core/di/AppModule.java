package com.przemyslawlusnia.vocabularycreator.core.di;

import android.app.Application;
import android.support.annotation.NonNull;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
  private Application application;
  private WordsRepository wordsRealmRepository;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @AppScope
  Application provideApplication() {
    return application;
  }

  @Provides
  @AppScope
  WordViewMapper wordViewMapper() {
    return new WordViewMapper();
  }

  @Provides
  @AppScope
  WordDomainMapper wordDomainMapper() {
    return new WordDomainMapper();
  }

  @Provides
  @AppScope
  WordsRepository wordsRealmRepository(WordDomainMapper wordMapper) {
    return getWordsRepository(wordMapper);
  }

  @NonNull
  private WordsRepository getWordsRepository(WordDomainMapper wordMapper) {
    if (wordsRealmRepository == null) {
      wordsRealmRepository = new WordsRealmRepository(wordMapper);
    }
    return wordsRealmRepository;
  }

}