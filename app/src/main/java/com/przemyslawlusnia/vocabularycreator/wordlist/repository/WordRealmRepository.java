package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.data.RealmRepository;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.ModifiableWordViewModel;
import io.realm.RealmResults;
import java.util.List;

public class WordRealmRepository extends RealmRepository {

  public void delete(List<ModifiableWordViewModel> words) {
    openRealmIfClosed();
    for (ModifiableWordViewModel word : words) {
      RealmResults<WordRealm> result = realm.where(WordRealm.class)
          .equalTo(WordRealm.KEY_WORD, word.getWord())
          .equalTo(WordRealm.KEY_TRANSLATION, word.getTranslation())
          .findAll();
      realm.executeTransaction(realm -> result.deleteFirstFromRealm());
    }
    closeRealm();
  }
}