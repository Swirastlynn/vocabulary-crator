package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import com.przemyslawlusnia.vocabularycreator.data.RealmRepository;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import io.realm.RealmResults;
import java.util.List;
import rx.Observable;

public class WordsRealmRepository extends RealmRepository implements WordsRepository {

  private WordDomainMapper mapper;

  public WordsRealmRepository(WordDomainMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public void delete(List<WordDomainModel> words) {
    openRealmIfClosed();
    for (WordDomainModel word : words) {
      RealmResults<WordRealm> result = realm.where(WordRealm.class)
          .equalTo(WordRealm.KEY_WORD, word.getWord())
          .equalTo(WordRealm.KEY_TRANSLATION, word.getTranslation())
          .findAll();
      realm.executeTransaction(realm -> result.deleteFirstFromRealm());
    }
    closeRealm();
  }

  @Override
  public void add(WordDomainModel wordDomainModel) {
    final WordRealm wordRealm = WordRealm.mapToWordRealm(wordDomainModel);
    openRealmIfClosed();
    transaction = realm.executeTransactionAsync(
        realm -> realm.copyToRealmOrUpdate(wordRealm),
        this::onSuccessTransaction,
        this::onFailureTransaction);
  }

  @Override
  public Observable<List<WordDomainModel>> getAllWords() {
    openRealmIfClosed();
    RealmResults<WordRealm> allWords = realm.where(WordRealm.class).findAll();
    final List<WordRealm> result = allWords.subList(0, allWords.size());
    closeRealm();
    return Observable.just(mapper.mapToWordDomainModels(result));
  }
}