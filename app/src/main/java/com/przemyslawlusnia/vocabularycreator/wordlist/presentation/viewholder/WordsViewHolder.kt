package com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.WordViewModel

abstract class WordsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var wordTxt: TextView = itemView.findViewById(R.id.word) as TextView
    var translationTxt: TextView = itemView.findViewById(R.id.translation) as TextView

    fun bind(word: WordViewModel) {
        wordTxt.text = word.word
        translationTxt.text = word.translation
    }

}