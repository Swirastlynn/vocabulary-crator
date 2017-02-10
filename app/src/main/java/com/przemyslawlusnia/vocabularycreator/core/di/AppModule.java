package com.przemyslawlusnia.vocabularycreator.core.di;

import android.app.Application;
import android.support.annotation.NonNull;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {
  private Application application;
  private WordsRealmRepository wordsRealmRepository;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides
  @AppScope
  public Application provideApplication() {
    return application;
  }

  @Provides
  @AppScope
  WordsRealmRepository wordsRealmRepository() {
    return getWordsRepository();
  }

  @NonNull
  private WordsRealmRepository getWordsRepository() { // todo mapper?
    if (wordsRealmRepository == null) {
      wordsRealmRepository = new WordsRealmRepository();
    }
    return wordsRealmRepository;
  }

}