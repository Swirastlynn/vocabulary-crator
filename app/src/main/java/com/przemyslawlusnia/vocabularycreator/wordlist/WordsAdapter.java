package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import com.przemyslawlusnia.vocabularycreator.wordlist.viewholder.LearnedWordRecyclerViewHolder;
import com.przemyslawlusnia.vocabularycreator.wordlist.viewholder.TrainingWordRecyclerViewHolder;
import com.przemyslawlusnia.vocabularycreator.wordlist.viewholder.WordsViewHolder;
import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final String TAG = WordsAdapter.class.getSimpleName();
  private List<Word> words;
  private final WordsView wordsView; // todo handle item click

  public WordsAdapter(WordsView wordsView) {
    words = new ArrayList<>();
    this.wordsView = wordsView;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == Word.TYPE_TRAINING) {
      return new TrainingWordRecyclerViewHolder(parent);
    } else if (viewType == Word.TYPE_LEARNED) {
      return new LearnedWordRecyclerViewHolder(parent);
    }
    Log.e(TAG, "Unknown Word type");
    return new TrainingWordRecyclerViewHolder(parent);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ((WordsViewHolder) holder).bind(words.get(position));
  }

  @Override
  public int getItemViewType(int position) {
    Word word = words.get(position);
    return word.getType();
  }

  @Override
  public int getItemCount() {
    return words.size();
  }

  public void setWords(List<Word> words) {
    this.words = words;
    notifyDataSetChanged();
  }

}
