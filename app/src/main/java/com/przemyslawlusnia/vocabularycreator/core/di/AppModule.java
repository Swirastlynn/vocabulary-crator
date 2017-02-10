package com.przemyslawlusnia.vocabularycreator.core.di;

import android.app.Application;
import android.support.annotation.NonNull;
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
  WordsRepository wordsRealmRepository() {
    return getWordsRepository();
  }

  @NonNull
  private WordsRepository getWordsRepository() { // todo mapper?
    if (wordsRealmRepository == null) {
      wordsRealmRepository = new WordsRealmRepository();
    }
    return wordsRealmRepository;
  }

}