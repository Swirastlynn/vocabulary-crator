package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import com.przemyslawlusnia.vocabularycreator.core.Constants;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.LearnedWordRecyclerViewHolder;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.TrainingWordRecyclerViewHolder;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.WordsViewHolder;
import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final String TAG = WordsAdapter.class.getSimpleName();
  private final WordsView wordsView;
  private List<ModifiableWordViewModel> words;
  private int selectedItemsCount;

  public WordsAdapter(WordsView wordsView) {
    words = new ArrayList<>();
    this.wordsView = wordsView;
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == Constants.TYPE_TRAINING) {
      return new TrainingWordRecyclerViewHolder(parent);
    } else if (viewType == Constants.TYPE_LEARNED) {
      return new LearnedWordRecyclerViewHolder(parent);
    }
    Log.e(TAG, "Unknown Word type");
    return new TrainingWordRecyclerViewHolder(parent);
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    final ModifiableWordViewModel word = words.get(position);
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
    ModifiableWordViewModel word = words.get(position);
    return word.getType();
  }

  @Override
  public int getItemCount() {
    return words.size();
  }

  public void setWords(List<ModifiableWordViewModel> words) {
    this.words = words;
    notifyDataSetChanged();
  }

  public void addWord(WordViewModel word) {
    words.add((ModifiableWordViewModel) word);
    notifyDataSetChanged();
  }

  public void deleteSelectedWords() {
    for (int i = words.size() - 1; i >= 0; i--) {
      ModifiableWordViewModel word = words.get(i);
      if (word.isSelected()) {
        words.remove(word);
        selectedItemsCount--;
      }
    }
    notifyDataSetChanged();
  }

  public WordViewModel getSelectedWord() {
    int selectedIndex = getFirstSelectedIndex();
    return selectedIndex >= 0 ? words.get(getFirstSelectedIndex()) : ModifiableWordViewModel.create();
  }

  public List<WordViewModel> getSelectedWords() {
    List<WordViewModel> result = new ArrayList<>();
    for (ModifiableWordViewModel word : words) {
      if (word.isSelected()) {
        result.add(word);
      }
    }
    return result;
  }

  private int getFirstSelectedIndex() {
    for (int i = 0; i < words.size(); i++) {
      if (words.get(i).isSelected()) {
        return i;
      }
    }
    return -1;
  }

  public void editSelectedWord(WordViewModel word) {
    words.set(getFirstSelectedIndex(), (ModifiableWordViewModel) word);
    notifyDataSetChanged();
  }
}
