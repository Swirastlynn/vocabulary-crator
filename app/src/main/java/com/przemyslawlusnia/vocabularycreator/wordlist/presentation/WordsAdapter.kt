package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.przemyslawlusnia.vocabularycreator.core.Constants
import com.przemyslawlusnia.vocabularycreator.core.utils.ObjectUtils
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.LearnedWordRecyclerViewHolder
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.TrainingWordRecyclerViewHolder
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.WordsViewHolder
import java.util.*

class WordsAdapter(private val listener: OnWordsSelectionListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG = WordsAdapter::class.java.simpleName

    private var words: MutableList<WordViewModel> = ArrayList()
    private var selectedItemsCount: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == Constants.TYPE_TRAINING) {
            return TrainingWordRecyclerViewHolder(parent)
        } else if (viewType == Constants.TYPE_LEARNED) {
            return LearnedWordRecyclerViewHolder(parent)
        }
        Log.e(TAG, "Unknown Word type")
        return TrainingWordRecyclerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val word = words[position]
        (holder as WordsViewHolder).bind(word)
        holder.itemView.isSelected = word.isSelected
        holder.itemView.setOnClickListener { view ->
            val newSelection = !word.isSelected
            word.isSelected = newSelection
            view.isSelected = newSelection
            updateSelection(if (newSelection) selectedItemsCount + 1 else selectedItemsCount - 1)
        }
    }

    private fun updateSelection(newSelectedItemsCount: Int) {
        selectedItemsCount = newSelectedItemsCount

        if (ObjectUtils.isNotNull(TAG, "updateSelection", listener)) {
            if (selectedItemsCount > 1) {
                listener.updateMultipleSelection()
            } else if (selectedItemsCount == 1) {
                listener.updateSingleSelection()
            } else {
                listener.updateNoSelection()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val (_, _, type) = words[position]
        return type
    }

    override fun getItemCount(): Int {
        return words.size
    }

    fun setWords(words: List<WordViewModel>) {
        this.words = words as MutableList<WordViewModel>
        notifyDataSetChanged()
    }

    fun addWord(word: WordViewModel) {
        words.add(word)
        notifyDataSetChanged()
    }

    fun clearSelection() {
        updateSelection(0)
    }

    val selectedWord: WordViewModel
        get() {
            val selectedIndex = firstSelectedIndex
            return if (selectedIndex >= 0)
                words[selectedIndex]
            else
                WordViewModel("", "", Constants.TYPE_TRAINING, false)
        }

    val selectedWords: List<WordViewModel>
        get() {
            return words.filter { it.isSelected }
        }

    private val firstSelectedIndex: Int
        get() {
            return words.indices.firstOrNull { words[it].isSelected } ?: -1
        }

    fun editSelectedWord(word: WordViewModel) {
        words[firstSelectedIndex] = word
        notifyDataSetChanged()
    }

}
