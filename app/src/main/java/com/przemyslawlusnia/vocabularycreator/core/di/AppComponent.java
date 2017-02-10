package com.przemyslawlusnia.vocabularycreator.core.di;

import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsDomainComponent;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsDomainModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        AppModule.class
    }
)
public interface AppComponent {

  WordsDomainComponent plus(WordsDomainModule module);

}