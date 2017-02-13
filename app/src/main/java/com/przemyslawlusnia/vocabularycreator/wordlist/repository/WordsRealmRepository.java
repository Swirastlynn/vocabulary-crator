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
  public Observable<Boolean> delete(List<WordDomainModel> words) {
    openRealmIfClosed();
    try { // todo but cancellation results in correct deletion...
      for (WordDomainModel word : words) {
        RealmResults<WordRealm> result = realm.where(WordRealm.class) // todo optimize
            .equalTo(WordRealm.KEY_WORD, word.getWord())
            .equalTo(WordRealm.KEY_TRANSLATION, word.getTranslation())
            .findAll();
        realm.executeTransaction(realm -> result.deleteFirstFromRealm());
      }
    } catch (Exception e) {
      return Observable.just(false);
    } finally {
      closeRealm();
    }
    return Observable.just(true);
  }

  @Override
  public Observable<Boolean> add(WordDomainModel wordDomainModel) {
    final WordRealm wordRealm = WordRealm.mapToWordRealm(wordDomainModel);
    openRealmIfClosed();
    try {
      realm.executeTransaction(realm -> realm.copyToRealmOrUpdate(wordRealm));
    } catch (Exception e) {
      return Observable.just(false);
    } finally {
      closeRealm();
    }
    return Observable.just(true);
  }

  @Override
  public Observable<List<WordDomainModel>> getAllWords() {
    openRealmIfClosed();
    RealmResults<WordRealm> allWords = realm.where(WordRealm.class).findAll();
    List<WordRealm> result = allWords.subList(0, allWords.size());
    Observable<List<WordDomainModel>> observable = Observable.just(mapper.mapToWordDomainModels(result));
    closeRealm();
    return observable;
  }
}