package com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewModel;

public abstract class WordsViewHolder extends RecyclerView.ViewHolder {

  @BindView(R.id.word)
  public TextView wordTxt;
  @BindView(R.id.translation)
  public TextView translationTxt;

  public WordsViewHolder(View itemView) {
    super(itemView);
  }

  public abstract void bind(WordViewModel word);

}