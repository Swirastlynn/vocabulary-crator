package com.przemyslawlusnia.vocabularycreator;

import com.przemyslawlusnia.vocabularycreator.wordlist.WordListFragmentComponent;
import com.przemyslawlusnia.vocabularycreator.wordlist.WordListFragmentModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(
    modules = {
        AppModule.class
    }
)
public interface AppComponent {

  WordListFragmentComponent plus(WordListFragmentModule module);

}