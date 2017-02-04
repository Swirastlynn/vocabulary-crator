package com.przemyslawlusnia.vocabularycreator.wordlist;

import org.immutables.value.Value;

@Value.Modifiable
public abstract class AbstractWord {
  public static final int TYPE_TRAINING = 0;
  public static final int TYPE_LEARNED = 1;

  public abstract String getWord();

  public abstract String getTranslation();

  public abstract int getType();

  public abstract boolean isSelected();
}