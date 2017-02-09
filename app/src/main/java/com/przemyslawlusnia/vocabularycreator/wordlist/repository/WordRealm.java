package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.ModifiableWordViewModel;
import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import io.realm.annotations.Required;
import java.util.UUID;

@RealmClass
public class WordRealm implements RealmModel {

  public static final String KEY_WORD = "word";
  public static final String KEY_TRANSLATION = "translation";

  @PrimaryKey
  @Required
  private String id;
  @Required
  private String word;
  private String translation;
  @Required
  private Integer type;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public String getTranslation() {
    return translation;
  }

  public void setTranslation(String translation) {
    this.translation = translation;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public static WordRealm createRealmAddress(ModifiableWordViewModel wordViewModel) {
    WordRealm wordRealm = new WordRealm();
    wordRealm.setId(UUID.randomUUID().toString());
    wordRealm.setWord(wordViewModel.getWord());
    wordRealm.setTranslation(wordViewModel.getTranslation());
    wordRealm.setType(wordViewModel.getType());
    return wordRealm;
  }
}
