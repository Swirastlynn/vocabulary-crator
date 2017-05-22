package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import com.przemyslawlusnia.vocabularycreator.core.Constants;
import com.przemyslawlusnia.vocabularycreator.core.utils.ObjectUtils;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.LearnedWordRecyclerViewHolder;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.TrainingWordRecyclerViewHolder;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.WordsViewHolder;
import java.util.ArrayList;
import java.util.List;

public class WordsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private static final String TAG = WordsAdapter.class.getSimpleName();
  private final OnWordsSelectionListener listener;
  private List<WordViewModel> words;
  private int selectedItemsCount;

  public WordsAdapter(OnWordsSelectionListener wordsView) {
    words = new ArrayList<>();
    this.listener = wordsView;
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
    final WordViewModel word = words.get(position);
    ((WordsViewHolder) holder).bind(word);
    holder.itemView.setSelected(word.isSelected());
    holder.itemView.setOnClickListener((view) -> {
      boolean newSelection = !word.isSelected();
      word.setSelected(newSelection);
      view.setSelected(newSelection);
      updateSelection(newSelection ? selectedItemsCount + 1 : selectedItemsCount - 1);
    });
  }

  private void updateSelection(int newSelectedItemsCount) {
    selectedItemsCount = newSelectedItemsCount;

    if (ObjectUtils.isNotNull(TAG, "updateSelection", listener)) {
      if (selectedItemsCount > 1) {
        listener.updateMultipleSelection();
      } else if (selectedItemsCount == 1) {
        listener.updateSingleSelection();
      } else {
        listener.updateNoSelection();
      }
    }
  }

  @Override
  public int getItemViewType(int position) {
    WordViewModel word = words.get(position);
    return word.getType();
  }

  @Override
  public int getItemCount() {
    return words.size();
  }

  public void setWords(List<WordViewModel> words) {
    this.words = words;
    notifyDataSetChanged();
  }

  public void addWord(WordViewModel word) {
    words.add(word);
    notifyDataSetChanged();
  }

  public void clearSelection() {
    updateSelection(0);
  }

  public WordViewModel getSelectedWord() {
    int selectedIndex = getFirstSelectedIndex();
    return selectedIndex >= 0 ? words.get(selectedIndex) : new WordViewModel();
  }

  public List<WordViewModel> getSelectedWords() {
    List<WordViewModel> result = new ArrayList<>();
    for (WordViewModel word : words) {
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
    words.set(getFirstSelectedIndex(), word);
    notifyDataSetChanged();
  }
}
