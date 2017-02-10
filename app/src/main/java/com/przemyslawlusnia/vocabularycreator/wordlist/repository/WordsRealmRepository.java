package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.data.RealmRepository;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.ModifiableWordViewModel;
import io.realm.RealmResults;
import java.util.List;
import rx.Observable;

public class WordsRealmRepository extends RealmRepository implements WordsRepository {

  @Override
  public void add(WordRealm wordRealm) {
    openRealmIfClosed();
    transaction = realm.executeTransactionAsync(
        realm -> realm.copyToRealmOrUpdate(wordRealm),
        this::onSuccessTransaction,
        this::onFailureTransaction);
  }

  @Override
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

  @Override
  public Observable<List<WordRealm>> getAllWords() {
    openRealmIfClosed();
    RealmResults<WordRealm> allWords = realm.where(WordRealm.class).findAll();
    final List<WordRealm> result = allWords.subList(0, allWords.size());
    closeRealm();
    return Observable.just(result);
  }
}