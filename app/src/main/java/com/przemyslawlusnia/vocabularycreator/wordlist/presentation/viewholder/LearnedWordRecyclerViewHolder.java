package com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewModel;

public class LearnedWordRecyclerViewHolder extends WordsViewHolder {

  public LearnedWordRecyclerViewHolder(ViewGroup parent) {
    super(LayoutInflater.from(parent.getContext()).inflate(R.layout.learned_word_item, parent, false));
  }

  @Override
  public void bind(WordViewModel word) {
    wordTxt.setText(word.getWord());
    translationTxt.setText(word.getTranslation());
  }
}