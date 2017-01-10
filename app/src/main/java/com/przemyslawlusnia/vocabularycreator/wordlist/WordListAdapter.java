package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.R;
import java.util.ArrayList;
import java.util.List;

public class WordListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private List<Pair<String, String>> words = new ArrayList<>();

  public WordListAdapter(List<Pair<String, String>> words) {
    this.words = words;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new TrainingWordViewHolder(
        LayoutInflater.from(parent.getContext()).inflate(R.layout.training_word_item, parent, false)
    );
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (holder instanceof TrainingWordViewHolder) {
      ((TrainingWordViewHolder) holder).wordTxt.setText(words.get(position).first);
      ((TrainingWordViewHolder) holder).translationTxt.setText(words.get(position).second);
    }
  }

  @Override
  public int getItemCount() {
    return words.size();
  }

  public static class TrainingWordViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.word)
    TextView wordTxt;
    @BindView(R.id.translation)
    TextView translationTxt;


    public TrainingWordViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
