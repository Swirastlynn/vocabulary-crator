package com.przemyslawlusnia.vocabularycreator;

import com.przemyslawlusnia.vocabularycreator.wordlist.WordsFragmentComponent;
import com.przemyslawlusnia.vocabularycreator.wordlist.WordsFragmentModule;
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