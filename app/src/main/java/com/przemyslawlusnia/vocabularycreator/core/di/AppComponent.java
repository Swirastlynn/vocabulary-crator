package com.przemyslawlusnia.vocabularycreator.core.di;

import android.app.Application;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import dagger.Component;

@AppScope
@Component(modules = {AppModule.class})
public interface AppComponent {

  void inject(Application app);

  Application provideApplication();

  WordViewMapper wordViewMapper();

  WordDomainMapper wordDomainMapper();

  WordsRepository wordsRealmRepository();

}