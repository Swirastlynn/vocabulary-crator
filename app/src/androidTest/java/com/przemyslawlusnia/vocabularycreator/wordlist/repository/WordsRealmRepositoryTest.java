package com.przemyslawlusnia.vocabularycreator.wordlist.repository;

import android.support.annotation.NonNull;
import com.przemyslawlusnia.vocabularycreator.core.Constants;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainMapper;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import rx.observers.TestSubscriber;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

// Tests created for purity. Personally, I would avoid such tests in production, because it costs time,
// and logic inside WordsRealmRepository is so simple...
// Ok. Maybe I would test delete method, but not add one.
public class WordsRealmRepositoryTest {

  Realm realm;
  WordsRealmRepository tested;
  WordDomainMapper mapper;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    RealmConfiguration config = new RealmConfiguration.Builder().inMemory().name("test-realm").build();
    Realm.setDefaultConfiguration(config);
    realm = Realm.getDefaultInstance();

    mapper = new WordDomainMapper();
    tested = new WordsRealmRepository(mapper);
  }

  @After
  public void tearDown() throws Exception {
    realm.close();
  }

  @Test
  public void add() throws Exception {
    WordDomainModel expected = getDummyWordDomainModel();
    tested.add(expected);

    WordRealm result = realm.where(WordRealm.class).findAll().get(0);
    WordDomainModel actual = mapper.mapToWordDomainModel(result);
    assertThat(actual, equalTo(expected));
  }

  @Test
  public void delete() throws Exception {
    WordDomainModel expected = getDummyWordDomainModel();
    tested.add(expected);
    tested.delete(Collections.singletonList(expected));

    RealmResults<WordRealm> results = realm.where(WordRealm.class).findAll();
    assertThat(results, equalTo(Collections.emptyList()));
  }

  @NonNull
  private WordDomainModel getDummyWordDomainModel() {
    return new WordDomainModel("test_word", "test_translation", Constants.TYPE_TRAINING);
  }

  @Test
  public void getAllWords() throws Exception {
    WordDomainModel expectedWord = getDummyWordDomainModel();
    List<WordDomainModel> expectedWordList = Collections.singletonList(expectedWord);
    tested.add(expectedWord);
    TestSubscriber<List<WordDomainModel>> testSubscriber = new TestSubscriber<>();
    tested.getAllWords().subscribe(testSubscriber);
    testSubscriber.assertValues(expectedWordList);
  }

}