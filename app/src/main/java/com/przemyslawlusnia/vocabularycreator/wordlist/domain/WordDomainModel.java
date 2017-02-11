package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.core.Constants;
import org.immutables.value.Value;

@Value.Immutable
public abstract class WordDomainModel {

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

}