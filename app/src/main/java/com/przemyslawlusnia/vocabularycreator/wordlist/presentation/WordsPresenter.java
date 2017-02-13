package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import com.przemyslawlusnia.vocabularycreator.core.BasePresenter;
import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

public class WordsPresenter extends BasePresenter<WordsView> {

  private static final String TAG = WordsPresenter.class.getSimpleName();

  private final WordViewMapper mapper;
  private final AddWordUseCase addWordUseCase;
  private final DeleteWordUseCase deleteWordUseCase;
  private final RxUseCase<List<WordDomainModel>> getAllWordsUseCase;
  private final rx.Scheduler uiScheduler;
  private Subscription addWordSubscription = Subscriptions.empty();
  private Subscription deleteWordSubscription = Subscriptions.empty();
  private Subscription getAllWordsSubscription = Subscriptions.empty();

  public WordsPresenter(WordsView wordsView,
                        WordViewMapper mapper,
                        rx.Scheduler uiScheduler,
                        AddWordUseCase addWordUseCase,
                        DeleteWordUseCase deleteWordUseCase,
                        RxUseCase<List<WordDomainModel>> getAllWordsUseCase) {
    this.view = wordsView;
    this.mapper = mapper;
    this.uiScheduler = uiScheduler;
    this.addWordUseCase = addWordUseCase;
    this.deleteWordUseCase = deleteWordUseCase;
    this.getAllWordsUseCase = getAllWordsUseCase;
  }

  public void addWord(WordViewModel wordViewModel) {
    view.showProgress();
    addWordUseCase.init(mapper.mapToWordDomainModel(wordViewModel));
    addWordSubscription = addWordUseCase.execute()
        .observeOn(uiScheduler)
        .subscribe(new AddWordObserver(wordViewModel));
  }

  public void deleteWords(List<WordViewModel> wordViewModels) {
    view.showProgress();
    deleteWordUseCase.init(mapper.mapToWordDomainModels(wordViewModels));
    deleteWordSubscription = deleteWordUseCase.execute()
        .observeOn(uiScheduler)
        .subscribe(new DeleteWordObserver());
  }

  public void getAllWords() {
    view.showProgress();
    getAllWordsSubscription = getAllWordsUseCase.execute()
        .map(wordDomainModels -> mapper.mapToWordViewModels(wordDomainModels))
        .observeOn(uiScheduler)
        .subscribe(new GetAllWordsObserver());
  }

  @Override
  public void onUnsubscribe() {
    if (!deleteWordSubscription.isUnsubscribed()) {
      deleteWordSubscription.unsubscribe();
    }
    if (!getAllWordsSubscription.isUnsubscribed()) {
      getAllWordsSubscription.unsubscribe();
    }
    if (!addWordSubscription.isUnsubscribed()) {
      addWordSubscription.unsubscribe();
    }
  }

  // Observers

  private class AddWordObserver implements Observer<Boolean> {
    private final WordViewModel wordViewModel;

    public AddWordObserver(WordViewModel wordViewModel) {
      this.wordViewModel = wordViewModel;
    }

    @Override
    public void onCompleted() {
      view.hideProgress();
    }

    @Override
    public void onError(Throwable t) {
      view.hideProgress();
      view.showFailure(t.getMessage());
    }

    @Override
    public void onNext(Boolean success) {
      if (success) {
        view.showAddWord(wordViewModel);
      }
    }
  }

  private class GetAllWordsObserver implements Observer<List<WordViewModel>> {

    @Override
    public void onCompleted() {
      view.hideProgress();
    }

    @Override
    public void onError(Throwable t) {
      view.hideProgress();
      view.showFailure(t.getMessage());
    }

    @Override
    public void onNext(List<WordViewModel> wordViewModels) {
      view.showAllWords(wordViewModels);
    }

  }

  private class DeleteWordObserver implements Observer<Void> {

    @Override
    public void onCompleted() {
      view.showDeletedWords();
      view.hideProgress();
    }

    @Override
    public void onError(Throwable t) {
      view.hideProgress();
      view.showFailure(t.getMessage());
    }

    @Override
    public void onNext(Void aVoid) {
      // nothing
    }
  }
}
