package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.core.Constants;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public class AddWordUseCaseTest {

  AddWordUseCase tested;

  @Mock
  WordsRepository wordsRepositoryMock;

  WordDomainModel testedWordDomainModel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    tested = new AddWordUseCase(Schedulers.immediate(), wordsRepositoryMock);
    testedWordDomainModel = new WordDomainModel("test_word", "test_translation", Constants.TYPE_TRAINING);
    tested.init(testedWordDomainModel);
  }

  @Test
  public void buildUseCaseObservable() throws Exception {
    when(wordsRepositoryMock.add(testedWordDomainModel)).thenReturn(Observable.just(true));
    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
    tested.buildUseCaseObservable().subscribe(testSubscriber);
    testSubscriber.assertValue(true);
  }

  @Test
  public void buildUseCaseObservable_cannotAddToRepository() throws Exception {
    when(wordsRepositoryMock.add(testedWordDomainModel)).thenReturn(Observable.just(false));
    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
    tested.buildUseCaseObservable().subscribe(testSubscriber);
    testSubscriber.assertValue(false);
  }

  @Test
  public void buildUseCaseObservable_emptyWord() throws Exception {
    WordDomainModel testedWordDomainModelWithEmptyWord =
        new WordDomainModel("", "test_translation", Constants.TYPE_TRAINING);
    when(wordsRepositoryMock.add(testedWordDomainModelWithEmptyWord)).thenReturn(Observable.just(true));
    tested.init(testedWordDomainModelWithEmptyWord);
    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
    tested.buildUseCaseObservable().subscribe(testSubscriber);
    testSubscriber.assertError(Throwable.class);
  }

}