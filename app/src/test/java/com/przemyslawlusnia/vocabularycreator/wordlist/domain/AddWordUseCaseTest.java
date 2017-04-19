package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

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

  ImmutableWordDomainModel testedWordDomainModel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    tested = new AddWordUseCase(Schedulers.immediate(), wordsRepositoryMock);
    testedWordDomainModel = ImmutableWordDomainModel.builder().word("test_word").translation("test_translation").build();
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
    ImmutableWordDomainModel testedWordDomainModelWithEmptyWord =
        ImmutableWordDomainModel.builder().word("").translation("test_translation").build();
    when(wordsRepositoryMock.add(testedWordDomainModelWithEmptyWord)).thenReturn(Observable.just(true));
    tested.init(testedWordDomainModelWithEmptyWord);
    TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
    tested.buildUseCaseObservable().subscribe(testSubscriber);
    testSubscriber.assertError(Throwable.class);
  }

}