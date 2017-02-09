package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.di.FragmentScope;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordsFragment;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
    modules = WordsFragmentModule.class
)
public interface WordsFragmentComponent {

  WordsFragment inject(WordsFragment wordsFragment);

}