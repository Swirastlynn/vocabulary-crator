package com.przemyslawlusnia.vocabularycreator.wordlist;

import org.immutables.value.Value;

@Value.Modifiable
public abstract class AbstractWord {
  public static final int TYPE_TRAINING = 0;
  public static final int TYPE_LEARNED = 1;

  @Value.Default
  public String getWord() {
    return "";
  }

  @Value.Default
  public String getTranslation() {
    return "";
  }

  @Value.Default
  public int getType() {
    return TYPE_TRAINING;
  }

  @Value.Default
  public boolean isSelected() {
    return false;
  }
}