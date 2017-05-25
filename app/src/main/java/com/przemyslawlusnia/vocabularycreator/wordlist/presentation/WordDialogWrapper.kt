package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import android.content.Context
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.view.View
import android.widget.EditText
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.core.Constants

class WordDialogWrapper(private val context: Context) {

    private val root: View = View.inflate(context, R.layout.word_translation_dialog, null)
    private val wordEditTxt: EditText = root.findViewById(R.id.wordEditTxt) as EditText
    private val translationEditTxt: EditText = root.findViewById(R.id.translationEditTxt) as EditText

    fun buildAndShow(titleId: Int, wordDialogListener: WordDialogListener, oldWord: WordViewModel?) {
        if (oldWord != null) {
            wordEditTxt.setText(oldWord.word)
            translationEditTxt.setText(oldWord.translation)
        }
        val dialog = AlertDialog.Builder(context)
                .setTitle(titleId)
                .setView(root)
                .setPositiveButton(R.string.ok) { dialogInterface, i ->
                    val wordText = wordEditTxt.text.toString()
                    val translationText = translationEditTxt.text.toString()
                    val newWord = WordViewModel("", "", Constants.TYPE_TRAINING, false)
                    newWord.translation = translationText
                    newWord.word = wordText
                    newWord.type = oldWord?.type ?: Constants.TYPE_TRAINING
                    newWord.isSelected = oldWord != null && oldWord.isSelected
                    wordDialogListener.positiveButtonClick(newWord)
                    dialogInterface.dismiss()
                }
                .setNegativeButton(R.string.cancel) { dialogInterface, _ ->
                    wordDialogListener.negativeButtonClick()
                    dialogInterface.dismiss()
                }.create()
        dialog.show()
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !wordEditTxt.text.toString().isEmpty()
        addTextChangeListener(dialog)
    }

    private fun addTextChangeListener(dialog: AlertDialog) {
        wordEditTxt.addTextChangedListener(object : AfterTextChangeWatcher() {
            override fun afterTextChanged(s: Editable?) {
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).isEnabled = !s.toString().isEmpty()
            }
        })
    }

}