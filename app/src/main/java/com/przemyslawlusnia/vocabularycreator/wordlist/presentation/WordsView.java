package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BaseView;
import java.util.List;

public interface WordsView extends BaseView {

  void showAddWord(WordViewModel wordViewModel);

  void showDeleteWords(List<WordViewModel> wordViewModels);

  void showAllWords(List<WordViewModel> wordViewModels);
}