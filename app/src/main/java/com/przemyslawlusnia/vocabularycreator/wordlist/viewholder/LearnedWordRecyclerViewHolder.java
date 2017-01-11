package com.przemyslawlusnia.vocabularycreator.wordlist.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.Word;

public class LearnedWordRecyclerViewHolder extends WordsViewHolder {

  @BindView(R.id.word)
  TextView wordTxt;
  @BindView(R.id.translation)
  TextView translationTxt;

  public LearnedWordRecyclerViewHolder(ViewGroup parent) {
    super(LayoutInflater.from(parent.getContext()).inflate(R.layout.learned_word_item, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void bind(Word word) {
    wordTxt.setText(word.word());
    translationTxt.setText(word.translation());
  }
}