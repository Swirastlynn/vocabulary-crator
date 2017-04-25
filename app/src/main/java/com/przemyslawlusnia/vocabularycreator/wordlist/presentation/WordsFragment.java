package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.core.ActivitiesAndFragmentsHelper;
import com.przemyslawlusnia.vocabularycreator.core.BaseFragment;
import com.przemyslawlusnia.vocabularycreator.core.utils.ViewUtils;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsDomainComponentProvider;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsViewModule;
import java.util.List;
import javax.inject.Inject;

public class WordsFragment extends BaseFragment implements WordsView, OnWordsSelectionListener {

  @BindView(R.id.wordsRecyclerView)
  RecyclerView wordsRecyclerView;

  @Inject
  WordsPresenter presenter;
  @Inject
  WordsAdapter wordsAdapter;
  @Inject
  LinearLayoutManager linearLayoutManager;

  private MenuMode menuMode;

  public static WordsFragment newInstance() {
    return new WordsFragment();
  }

  public WordsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
    setupDependencies();
    presenter.onAttachView(this);
  }

  private void setupDependencies() {
    WordsDomainComponentProvider.INSTANCE.getWordsDomainComponent()
        .plus(new WordsViewModule(this, this, (WordsActivity) getActivity()))
        .inject(this);
  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    if (menuMode == MenuMode.SingleSelection) {
      inflater.inflate(R.menu.words_selection_menu, menu);
    } else if (menuMode == MenuMode.MultipleSelection) {
      inflater.inflate(R.menu.words_selection_menu, menu);
      MenuItem actionEditMenuItem = menu.findItem(R.id.action_edit);
      actionEditMenuItem.setEnabled(false);
      ViewUtils.tintMenuItemIcon(R.color.gray, actionEditMenuItem);
    } else {
      inflater.inflate(R.menu.words_start_menu, menu);
    }
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_search:
        Toast.makeText(getContext(), "Action Search", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_settings:
        Toast.makeText(getContext(), "Action Settings", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_learn:
        Toast.makeText(getContext(), "Action Learn", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_train:
        Toast.makeText(getContext(), "Action Train", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_edit:
        buildAndShowAddWordDialog(wordsAdapter.getSelectedWord(), true);
        return true;
      case R.id.action_delete:
        presenter.deleteWords(wordsAdapter.getSelectedWords());
        return true;
      default:
        break;
    }

    return true;
  }

  @Override
  public void updateNoSelection() {
    if (menuMode != MenuMode.NoSelection) {
      menuMode = MenuMode.NoSelection;
      getActivity().supportInvalidateOptionsMenu();
    }
  }

  @Override
  public void updateSingleSelection() {
    menuMode = MenuMode.SingleSelection;
    getActivity().supportInvalidateOptionsMenu();
  }

  @Override
  public void updateMultipleSelection() {
    if (menuMode != MenuMode.MultipleSelection) {
      menuMode = MenuMode.MultipleSelection;
      getActivity().supportInvalidateOptionsMenu();
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.words_fragment, container, false);
    butterknifeUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    wordsRecyclerView.setLayoutManager(linearLayoutManager);
    presenter.getAllWords();
    wordsRecyclerView.setAdapter(wordsAdapter);
  }

  @OnClick(R.id.addWordFab)
  public void fabClick(View view) {
    buildAndShowAddWordDialog(ModifiableWordViewModel.create(), false);
  }

  private void buildAndShowAddWordDialog(WordViewModel oldWord, boolean edit) {
    WordDialogWrapper wordDialogWrapper = new WordDialogWrapper(getContext());
    wordDialogWrapper.buildAndShow(edit ? R.string.edit_word : R.string.new_word,
        new WordDialogWrapper.WordDialogListener() {
          @Override
          public void positiveButtonClick(WordViewModel word) {
            if (edit) {
              wordsAdapter.editSelectedWord(word);
            } else {
              presenter.addWord(word);
            }
            ActivitiesAndFragmentsHelper.hideKeyboard(getActivity());
          }

          @Override
          public void negativeButtonClick() {
            ActivitiesAndFragmentsHelper.hideKeyboard(getActivity());
          }
        }, oldWord
    );
    // todo RXJava reactive TextWatcher
    ActivitiesAndFragmentsHelper.showKeyboard(getActivity());
  }

  @Override
  public void showAddWord(WordViewModel wordViewModel) {
    wordsAdapter.addWord(wordViewModel);
  }

  @Override
  public void showAllWordsAfterDeletion() {
    wordsAdapter.clearSelection();
    presenter.getAllWords();
  }

  @Override
  public void showAllWords(List<WordViewModel> wordViewModels) {
    wordsAdapter.setWords(wordViewModels);
  }

  @Override
  public void showProgress() {
    // not used todo progressBar
  }

  @Override
  public void hideProgress() {
    // not used
  }

  @Override
  public void showFailure(String message) {
    // not used todo log
  }

  @Override
  public void onPause() {
    presenter.onUnsubscribe();
    super.onPause();
  }

  @Override
  public void onDestroy() {
    presenter.onDestroy();
    super.onDestroy();
  }
}
