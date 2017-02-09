package com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.ModifiableWordViewModel;

public class TrainingWordRecyclerViewHolder extends WordsViewHolder {

  @BindView(R.id.word)
  TextView wordTxt;
  @BindView(R.id.translation)
  TextView translationTxt;

  public TrainingWordRecyclerViewHolder(ViewGroup parent) {
    super(LayoutInflater.from(parent.getContext()).inflate(R.layout.training_word_item, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override
  public void bind(ModifiableWordViewModel word) {
    wordTxt.setText(word.getWord());
    translationTxt.setText(word.getTranslation());
  }
}
