package com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewModel;

public abstract class WordsViewHolder extends RecyclerView.ViewHolder {

  public WordsViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(WordViewModel word);

}