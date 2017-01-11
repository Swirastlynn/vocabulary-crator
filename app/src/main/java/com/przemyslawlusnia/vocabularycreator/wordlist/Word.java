package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Word {
  public static final int TYPE_TRAINING = 0;
  public static final int TYPE_LEARNED = 1;

  public abstract String getWord();

  public abstract String getTranslation();

  public abstract int getType();

  public static Word create(String word, String translation, int type) {
    return new AutoValue_Word(word, translation, type);
  }
}
