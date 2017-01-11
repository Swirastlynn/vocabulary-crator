package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
    modules = WordsFragmentModule.class
)
public interface WordsFragmentComponent {

  WordsFragment inject(WordsFragment wordsFragment);

}