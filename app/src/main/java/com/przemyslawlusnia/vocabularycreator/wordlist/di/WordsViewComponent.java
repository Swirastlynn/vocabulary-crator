package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsFragment;
import dagger.Subcomponent;

@WordsViewScope
@Subcomponent(modules = WordsViewModule.class)
public interface WordsViewComponent {

  void inject(WordsFragment wordsFragment);

}