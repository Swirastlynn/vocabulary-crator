package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.core.Constants;

public class WordDialogWrapper {

  private View root;
  private EditText wordEditTxt;
  private EditText translationEditTxt;
  private Context context;

  public WordDialogWrapper(Context context) {
    this.root = View.inflate(context, R.layout.word_translation_dialog, null);
    this.wordEditTxt = (EditText) root.findViewById(R.id.wordEditTxt);
    this.translationEditTxt = (EditText) root.findViewById(R.id.translationEditTxt);
    this.context = context;
  }

  AlertDialog.Builder build(int titleId, WordDialogListener wordDialogListener, WordViewModel oldWord) {
    if (oldWord != null) {
      wordEditTxt.setText(oldWord.getWord());
      translationEditTxt.setText(oldWord.getTranslation());
    }
    return new AlertDialog.Builder(context)
        .setTitle(titleId)
        .setView(root)
        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
          String wordText = wordEditTxt.getText().toString();
          String translationText = translationEditTxt.getText().toString();
          ModifiableWordViewModel newWord = ModifiableWordViewModel.create().setTranslation(translationText)
              .setWord(wordText)
              .setType(oldWord == null ? Constants.TYPE_TRAINING : oldWord.getType())
              .setIsSelected(oldWord != null && oldWord.isSelected());
          wordDialogListener.positiveButtonClick(newWord);
          dialogInterface.dismiss();
        })
        .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
          wordDialogListener.negativeButtonClick();
          dialogInterface.dismiss();
        });
  }

  public interface WordDialogListener {
    void positiveButtonClick(WordViewModel newWord);

    void negativeButtonClick();
  }
}
