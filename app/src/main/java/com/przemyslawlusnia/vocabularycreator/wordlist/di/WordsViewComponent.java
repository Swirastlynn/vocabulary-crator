package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.di.WordsFragmentScope;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsFragment;
import dagger.Component;

@WordsFragmentScope
@Component(
    modules = WordsViewModule.class,
    dependencies = WordsDomainComponent.class)

public interface WordsViewComponent {

  void inject(WordsFragment wordsFragment);

}