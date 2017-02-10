package com.przemyslawlusnia.vocabularycreator.core.di;

import android.app.Application;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {

  void inject(Application app);

  Application provideApplication();

  WordsRealmRepository wordsRealmRepository();

}