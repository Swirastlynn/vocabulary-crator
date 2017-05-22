package com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewModel;

public abstract class WordsViewHolder extends RecyclerView.ViewHolder {

  public TextView wordTxt;
  public TextView translationTxt;

  public WordsViewHolder(View itemView) {
    super(itemView);
    wordTxt = (TextView) itemView.findViewById(R.id.word);
    translationTxt = (TextView) itemView.findViewById(R.id.translation);
  }

  public abstract void bind(WordViewModel word);

}