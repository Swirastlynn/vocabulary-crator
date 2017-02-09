package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BaseView;

public interface WordsView extends BaseView {

  void updateMultipleSelection(); // todo sure? Such abstraction from presenter?

  void updateSingleSelection();

  void updateNoSelection();
}
