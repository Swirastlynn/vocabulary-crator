package com.przemyslawlusnia.vocabularycreator.wordlist.domain;

import com.przemyslawlusnia.vocabularycreator.core.Constants;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public class DeleteWordUseCaseTest {

  DeleteWordUseCase tested;

  @Mock
  WordsRepository wordsRepositoryMock;

  List<WordDomainModel> testedWordDomainModels = new ArrayList<>();

  @Before
  public void setUp() throws Exception { // todo https://youtrack.jetbrains.com/issue/KT-17951
    MockitoAnnotations.initMocks(this);
    tested = new DeleteWordUseCase(Schedulers.immediate(), wordsRepositoryMock);
    WordDomainModel testedWordDomainModel =
        new WordDomainModel("test_word", "test_translation", Constants.INSTANCE.getTYPE_TRAINING());
    testedWordDomainModels.add(testedWordDomainModel);
    tested.initialize(testedWordDomainModels);
  }

  @Test
  public void buildUseCaseObservable() throws Exception {
    when(wordsRepositoryMock.delete(testedWordDomainModels)).thenReturn(Observable.empty());
    TestSubscriber<Void> testSubscriber = new TestSubscriber<>();
    tested.buildUseCaseObservable().subscribe(testSubscriber);
    testSubscriber.assertNoValues();
  }

  @Test
  public void buildUseCaseObservable_nullList() throws Exception {
    testedWordDomainModels = null;
    tested.initialize(testedWordDomainModels);
    when(wordsRepositoryMock.delete(testedWordDomainModels)).thenReturn(Observable.empty());
    TestSubscriber<Void> testSubscriber = new TestSubscriber<>();
    tested.buildUseCaseObservable().subscribe(testSubscriber);
    testSubscriber.assertError(Throwable.class);
  }
}