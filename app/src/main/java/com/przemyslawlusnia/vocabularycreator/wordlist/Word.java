package com.przemyslawlusnia.vocabularycreator.wordlist;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Word { // todo parcelable with plugin
  public static final int TYPE_TRAINING = 0;
  public static final int TYPE_LEARNED = 1;

  public abstract String word(); // todo getters?

  public abstract String translation();

  public abstract int type();

  public static Word create(String word, String translation, int type) {
    return new AutoValue_Word(word, translation, type);
  }
}
