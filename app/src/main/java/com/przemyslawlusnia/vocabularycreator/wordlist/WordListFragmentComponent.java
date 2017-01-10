package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(
    modules = WordListFragmentModule.class
)
public interface WordListFragmentComponent {

  WordListFragment inject(WordListFragment wordListFragment);

}