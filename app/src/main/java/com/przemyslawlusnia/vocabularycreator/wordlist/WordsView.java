package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.przemyslawlusnia.vocabularycreator.BaseView;

public interface WordsView extends BaseView {

  void updateMultipleSelection();

  void updateSingleSelection();

  void updateNoSelection();
}
