package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import android.util.Log;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import io.realm.Realm;
import io.realm.RealmResults;
import java.util.Collections;
import java.util.List;
import rx.Observable;

public class WordsRealmRepository implements WordsRepository {

  private static final String TAG = WordsRealmRepository.class.getSimpleName();

  private WordDomainMapper mapper;

  public WordsRealmRepository(WordDomainMapper mapper) {
    this.mapper = mapper;
  }

  @Override
  public Observable<Void> delete(List<WordDomainModel> words) {
    try (Realm realmInstance = Realm.getDefaultInstance()) {
      for (int i = 0; i < words.size(); i++) {
        WordDomainModel wordDomainModel = words.get(i);
        RealmResults<WordRealm> result = realmInstance.where(WordRealm.class)
            .equalTo(WordRealm.KEY_WORD, wordDomainModel.getWord())
            .equalTo(WordRealm.KEY_TRANSLATION, wordDomainModel.getTranslation())
            .findAll();
        if (result.isEmpty()) {
          Log.e(TAG, "Such word do not exists in database");
        }
        realmInstance.executeTransaction(realm -> result.deleteAllFromRealm());
      }
    }
    return Observable.empty();
  }

  @Override
  public Observable<Boolean> add(WordDomainModel wordDomainModel) {
    final WordRealm wordRealm = WordRealm.mapToWordRealm(wordDomainModel);
    try (Realm realmInstance = Realm.getDefaultInstance()) {
      realmInstance.executeTransaction(realm -> realm.copyToRealmOrUpdate(wordRealm));
    } catch (Exception e) {
      return Observable.just(false);
    }
    return Observable.just(true);
  }

  @Override
  public Observable<List<WordDomainModel>> getAllWords() {
    Observable<List<WordDomainModel>> observable = Observable.just(Collections.emptyList());
    try (Realm realmInstance = Realm.getDefaultInstance()) {
      RealmResults<WordRealm> allWords = realmInstance.where(WordRealm.class).findAll();
      List<WordRealm> result = allWords.subList(0, allWords.size());
      observable = Observable.just(mapper.mapToWordDomainModels(result));
    } catch (Exception e) {
      Log.e(TAG, "Cannot get words from database");
    }
    return observable;
  }
}