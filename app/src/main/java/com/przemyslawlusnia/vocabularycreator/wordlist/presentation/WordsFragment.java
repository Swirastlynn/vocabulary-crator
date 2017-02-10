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
import com.przemyslawlusnia.vocabularycreator.core.Constants;
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication;
import com.przemyslawlusnia.vocabularycreator.core.utils.ViewUtils;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.DaggerWordsDomainComponent;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsDomainComponent;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsDomainModule;
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsViewModule;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class WordsFragment extends BaseFragment implements WordsView {

  @BindView(R.id.wordsRecyclerView)
  RecyclerView wordsRecyclerView;

  @Inject
  WordsPresenter presenter;
  @Inject
  WordsAdapter wordsAdapter;
  @Inject
  LinearLayoutManager linearLayoutManager;

  private WordsDomainComponent wordsDomainComponent;

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
    wordsDomainComponent = DaggerWordsDomainComponent
        .builder()
        .appComponent(VocabularyCreatorApplication.get(getActivity()).getAppComponent())
        .wordsDomainModule(new WordsDomainModule())
        .build();

    wordsDomainComponent
        .plus(new WordsViewModule(this, (WordsActivity) getActivity()))
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
        createAndShowAddWordDialog(wordsAdapter.getSelectedWord(), true);
        // todo database operation
        return true;
      case R.id.action_delete:
        presenter.deleteWord(wordsAdapter.getSelectedWords());
        wordsAdapter.deleteSelectedWords();
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
    wordsAdapter.setWords(getTempWordList());
    wordsRecyclerView.setAdapter(wordsAdapter);
  }

  private List<ModifiableWordViewModel> getTempWordList() {
    List<ModifiableWordViewModel> list = new ArrayList<>();
    list.add(ModifiableWordViewModel.create().setTranslation("obliterate").setWord("wymazać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("surreptitious").setWord("dyskretny").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("menacingly").setWord("groźnie").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("hush").setWord("cicho-sza").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("hush!").setWord("sza!").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("exert").setWord("wyciąg").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assure").setWord("zapewniać, ubezpieczać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assessment").setWord("oszacowanie").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("swivel").setWord("obracać się").setType(Constants.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assess").setWord("oszacować").setType(Constants.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assess").setWord("oszacować").setType(Constants.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assess").setWord("oszacować").setType(Constants.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assess").setWord("oszacować").setType(Constants.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWordViewModel.create().setTranslation("assert").setWord("zapewniać").setType(Constants.TYPE_TRAINING).setIsSelected(false));
    return list;
  }

  @OnClick(R.id.addWordFab)
  public void fabClick(View view) {
    createAndShowAddWordDialog(ModifiableWordViewModel.create(), false);
  }

  private void createAndShowAddWordDialog(ModifiableWordViewModel oldWord, boolean edit) {
    WordDialogWrapper wordDialogWrapper = new WordDialogWrapper(getContext());
    wordDialogWrapper.build(edit ? R.string.edit_word : R.string.new_word,
        new WordDialogWrapper.WordDialogListener() {
          @Override
          public void positiveButtonClick(ModifiableWordViewModel word) {
            if (edit) {
              wordsAdapter.editSelectedWord(word);
            } else {
              presenter.addWord(word);
              wordsAdapter.addWord(word);
            }
            ActivitiesAndFragmentsHelper.hideKeyboard(getActivity());
          }

          @Override
          public void negativeButtonClick() {
            ActivitiesAndFragmentsHelper.hideKeyboard(getActivity());
          }
        }, oldWord
    ).create().show();

    // todo RXJava reactive TextWatcher
    ActivitiesAndFragmentsHelper.showKeyboard(getActivity());
  }

  @Override
  public void showProgress() {
    // not used
  }

  @Override
  public void hideProgress() {
    // not used
  }

  @Override
  public void showFailure(Throwable t) {
    // not used
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
