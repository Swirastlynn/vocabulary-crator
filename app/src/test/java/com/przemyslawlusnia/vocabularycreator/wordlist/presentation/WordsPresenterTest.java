package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.annotation.NonNull;
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.ImmutableWordDomainModel;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rx.Observable;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class WordsPresenterTest {

  @Mock
  WordsView viewMock;
  @Mock
  AddWordUseCase addWordUseCaseMock;
  @Mock
  DeleteWordUseCase deleteWordUseCaseMock;
  @Mock
  RxUseCase<List<WordDomainModel>> getAllWordsUseCaseMock;

  WordsPresenter tested;

  // Can't mock it - PowerMockito or Mockito 2.x required, cause class is final
  ModifiableWordViewModel testedWordViewModel;
  ImmutableWordDomainModel testedWordDomainModel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    tested = new WordsPresenter(viewMock, new WordViewMapper(), Schedulers.immediate(),
        addWordUseCaseMock, deleteWordUseCaseMock, getAllWordsUseCaseMock);
    testedWordViewModel = ModifiableWordViewModel.create();
    testedWordViewModel = testedWordViewModel.setWord("test_word").setTranslation("test_translation");
    testedWordDomainModel = ImmutableWordDomainModel.builder().word("test_word").translation("test_translation").build();
    doNothing().when(viewMock).showProgress();
  }

  @Test
  public void addWord() throws Exception {
    when(addWordUseCaseMock.execute()).thenReturn(Observable.just(true));
    tested.addWord(testedWordViewModel);
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showAddWord(testedWordViewModel);
  }

  @Test
  public void addWord_cannot() throws Exception {
    when(addWordUseCaseMock.execute()).thenReturn(Observable.just(false));
    tested.addWord(testedWordViewModel);
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.never()).showAddWord(testedWordViewModel);
  }

  @Test
  public void deleteWords() throws Exception {
    when(deleteWordUseCaseMock.execute()).thenReturn(Observable.empty());
    tested.deleteWords(getListWithWordViewModel());
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showDeletedWords();
  }

  @Test
  public void deleteWords_nullcheck() throws Exception {
    when(deleteWordUseCaseMock.execute()).thenReturn(Observable.empty());
    tested.deleteWords(null);
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showDeletedWords();
  }

  @Test
  public void getAllWords() throws Exception {
    Observable<List<WordDomainModel>> observable = Observable.just(getListWithWordDomainModel());
    when(getAllWordsUseCaseMock.execute()).thenReturn(observable);
    tested.getAllWords();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showAllWords(getListWithWordViewModel());
  }

  @Test
  public void getAllWords_forEmptyList() throws Exception {
    Observable<List<WordDomainModel>> observable = Observable.just(Collections.emptyList());
    when(getAllWordsUseCaseMock.execute()).thenReturn(observable);
    tested.getAllWords();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showAllWords(Collections.emptyList());
  }

  @Test
  public void getAllWords_nullcheck() throws Exception {
    Observable<List<WordDomainModel>> observable = Observable.just(null);
    when(getAllWordsUseCaseMock.execute()).thenReturn(observable);
    tested.getAllWords();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).hideProgress();
    Mockito.verify(viewMock, Mockito.atLeastOnce()).showAllWords(Collections.emptyList());
  }


  @NonNull
  private ArrayList<WordViewModel> getListWithWordViewModel() {
    ArrayList<WordViewModel> list = new ArrayList<>();
    list.add(testedWordViewModel);
    return list;
  }

  @NonNull
  private ArrayList<WordDomainModel> getListWithWordDomainModel() {
    ArrayList<WordDomainModel> list = new ArrayList<>();
    list.add(testedWordDomainModel);
    return list;
  }

}