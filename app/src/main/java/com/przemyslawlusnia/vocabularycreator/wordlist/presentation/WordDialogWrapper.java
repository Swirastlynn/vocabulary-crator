package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
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

  void buildAndShow(int titleId, WordDialogListener wordDialogListener, WordViewModel oldWord) {
    if (oldWord != null) {
      wordEditTxt.setText(oldWord.getWord());
      translationEditTxt.setText(oldWord.getTranslation());
    }
    final AlertDialog dialog = new AlertDialog.Builder(context)
        .setTitle(titleId)
        .setView(root)
        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
          String wordText = wordEditTxt.getText().toString();
          String translationText = translationEditTxt.getText().toString();
          WordViewModel newWord = new WordViewModel("", "", Constants.INSTANCE.getTYPE_TRAINING(), false);
          newWord.setTranslation(translationText);
          newWord.setWord(wordText);
          newWord.setType(oldWord == null ? Constants.INSTANCE.getTYPE_TRAINING() : oldWord.getType());
          newWord.setSelected(oldWord != null && oldWord.isSelected());
          wordDialogListener.positiveButtonClick(newWord);
          dialogInterface.dismiss();
        })
        .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
          wordDialogListener.negativeButtonClick();
          dialogInterface.dismiss();
        }).create();
    dialog.show();
    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!wordEditTxt.getText().toString().isEmpty());
    addTextChangeListener(dialog);
  }

  private void addTextChangeListener(final AlertDialog dialog) {
    wordEditTxt.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // nothing
      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
        // nothing
      }

      @Override
      public void afterTextChanged(Editable s) {
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!s.toString().isEmpty());
      }
    });
  }

  public interface WordDialogListener {
    void positiveButtonClick(WordViewModel newWord);

    void negativeButtonClick();
  }
}
