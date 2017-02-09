package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BaseView;

public interface WordsView extends BaseView {

  void updateMultipleSelection();

  void updateSingleSelection();

  void updateNoSelection();
}
