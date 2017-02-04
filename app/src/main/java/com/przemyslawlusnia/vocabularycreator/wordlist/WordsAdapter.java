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
  private final WordsView wordsView;
  private List<ModifiableWord> words;
  private int selectedItemsCount;

  public WordsAdapter(WordsView wordsView) {
    words = new ArrayList<>();
    this.wordsView = wordsView;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == AbstractWord.TYPE_TRAINING) {
      return new TrainingWordRecyclerViewHolder(parent);
    } else if (viewType == AbstractWord.TYPE_LEARNED) {
      return new LearnedWordRecyclerViewHolder(parent);
    }
    Log.e(TAG, "Unknown Word type");
    return new TrainingWordRecyclerViewHolder(parent);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    final ModifiableWord word = words.get(position);
    ((WordsViewHolder) holder).bind(word);
    holder.itemView.setSelected(word.isSelected());
    holder.itemView.setOnClickListener((view) -> {
      boolean newSelection = !word.isSelected();
      word.setIsSelected(newSelection);
      view.setSelected(newSelection);
      selectedItemsCount = newSelection ? selectedItemsCount + 1 : selectedItemsCount - 1;
      if (selectedItemsCount > 1) {
        wordsView.updateMultipleSelection();
      } else if (selectedItemsCount == 1) {
        wordsView.updateSingleSelection();
      } else {
        wordsView.updateNoSelection();
      }
    });
  }

  @Override
  public int getItemViewType(int position) {
    ModifiableWord word = words.get(position);
    return word.getType();
  }

  @Override
  public int getItemCount() {
    return words.size();
  }

  public void setWords(List<ModifiableWord> words) {
    this.words = words;
    notifyDataSetChanged();
  }

  public void addWord(ModifiableWord word) {
    words.add(word);
    notifyDataSetChanged();
  }

  public void removeSelectedWords() {
    for (int i = words.size() - 1; i >= 0; i--) {
      ModifiableWord word = words.get(i);
      if (word.isSelected()) {
        words.remove(word);
        selectedItemsCount--;
      }
    }
    notifyDataSetChanged();
  }

  private int getFirstSelectedIndex() {
    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).isSelected()) {
        return i;
      }
    }
    return -1;
  }

  public ModifiableWord getSelectedWord() {
    int selectedIndex = getFirstSelectedIndex();
    return selectedIndex >= 0 ? words.get(getFirstSelectedIndex()) : ModifiableWord.create();
  }

  public void editSelectedWord(ModifiableWord word) {
    words.set(getFirstSelectedIndex(), word);
    notifyDataSetChanged();
  }
}
