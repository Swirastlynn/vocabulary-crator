package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import android.util.Log;
import com.przemyslawlusnia.vocabularycreator.data.RealmRepository;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import io.realm.RealmResults;
import java.util.Collections;
import java.util.List;
import rx.Observable;

public class WordsRealmRepository extends RealmRepository implements WordsRepository {

  private static final String TAG = WordsRealmRepository.class.getSimpleName();

  private WordDomainMapper mapper;

  public WordsRealmRepository(WordDomainMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Observable<Void> delete(List<WordDomainModel> words) {
    openRealmIfClosed();
    try {
      for (int i = 0; i < words.size(); i++) {
        WordDomainModel wordDomainModel = words.get(i);
        RealmResults<WordRealm> result = realm.where(WordRealm.class)
            .equalTo(WordRealm.KEY_WORD, wordDomainModel.getWord())
            .equalTo(WordRealm.KEY_TRANSLATION, wordDomainModel.getTranslation())
            .findAll();
        if (result.isEmpty()) {
          Log.e(TAG, "Such word do not exists in database");
        }
        realm.executeTransaction(realm -> result.deleteAllFromRealm());
      }
    } finally {
      closeRealm(); // common Realm idiom for non-UI threads usage
    }
    return Observable.empty();
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
    Observable<List<WordDomainModel>> observable = Observable.just(Collections.emptyList());
    try {
      RealmResults<WordRealm> allWords = realm.where(WordRealm.class).findAll();
      List<WordRealm> result = allWords.subList(0, allWords.size());
      observable = Observable.just(mapper.mapToWordDomainModels(result));
    } catch (Exception e) {
      Log.e(TAG, "Cannot get words from database");
    } finally {
      closeRealm();
    }
    return observable;
  }
}