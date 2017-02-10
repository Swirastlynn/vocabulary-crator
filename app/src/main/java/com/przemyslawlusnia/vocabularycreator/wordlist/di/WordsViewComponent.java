package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.di.WordsFragmentScope;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsFragment;
import dagger.Subcomponent;

@WordsFragmentScope
@Subcomponent(modules = WordsViewModule.class)
public interface WordsViewComponent {

  void inject(WordsFragment wordsFragment);

}