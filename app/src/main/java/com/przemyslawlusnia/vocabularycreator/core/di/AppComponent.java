package com.przemyslawlusnia.vocabularycreator.core.di;

import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsFragmentComponent;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsFragmentModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        AppModule.class
    }
)
public interface AppComponent {

  WordsFragmentComponent plus(WordsFragmentModule module);

}