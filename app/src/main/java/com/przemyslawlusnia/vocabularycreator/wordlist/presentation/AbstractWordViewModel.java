package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.Constants;
import org.immutables.value.Value;

@Value.Modifiable
public abstract class AbstractWordViewModel {

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
    return Constants.TYPE_TRAINING;
  }

  @Value.Default
  public boolean isSelected() {
    return false;
  }
}