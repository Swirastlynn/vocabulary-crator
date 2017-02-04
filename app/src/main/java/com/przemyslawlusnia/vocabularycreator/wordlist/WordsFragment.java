package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.przemyslawlusnia.vocabularycreator.ActivitiesAndFragmentsHelper;
import com.przemyslawlusnia.vocabularycreator.BaseFragment;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.VocabularyCreatorApplication;
import com.przemyslawlusnia.vocabularycreator.utils.ViewUtils;
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
    setupActivityComponent();
    presenter.onAttachView(this);
  }

  private void setupActivityComponent() {
    VocabularyCreatorApplication.get(getActivity())
        .getAppComponent()
        .plus(new WordsFragmentModule(this, (WordsActivity) getContext()))
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
        Toast.makeText(getContext(), "Action Edit", Toast.LENGTH_SHORT).show();
        return true;
      case R.id.action_delete:
        wordsAdapter.removeSelectedWords();
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

  private List<ModifiableWord> getTempWordList() {
    List<ModifiableWord> list = new ArrayList<>();
    list.add(ModifiableWord.create().setTranslation("obliterate").setWord("wymazać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("surreptitious").setWord("dyskretny").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("menacingly").setWord("groźnie").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("hush").setWord("cicho-sza").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("hush!").setWord("sza!").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("exert").setWord("wyciąg").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assure").setWord("zapewniać, ubezpieczać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assessment").setWord("oszacowanie").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("swivel").setWord("obracać się").setType(ModifiableWord.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assess").setWord("oszacować").setType(ModifiableWord.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assess").setWord("oszacować").setType(ModifiableWord.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assess").setWord("oszacować").setType(ModifiableWord.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assess").setWord("oszacować").setType(ModifiableWord.TYPE_LEARNED).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    list.add(ModifiableWord.create().setTranslation("assert").setWord("zapewniać").setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false));
    return list;
  }

  @OnClick(R.id.addWordFab)
  public void fabClick(View view) {
    createAndShowAddWordDialog();
  }

  private void createAndShowAddWordDialog() {
    View root = View.inflate(getActivity(), R.layout.word_edit_text, null);
    final EditText wordEditTxt = (EditText) root.findViewById(R.id.wordEditTxt);
    final EditText translationEditTxt = (EditText) root.findViewById(R.id.translationEditTxt);
    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
        .setTitle(R.string.new_word)
        .setView(root)
        .setPositiveButton(R.string.ok, (dialogInterface, i) -> {
          final String wordText = wordEditTxt.getText().toString();
          final String translationText = translationEditTxt.getText().toString();
          Toast.makeText(getActivity(), wordText, Toast.LENGTH_SHORT).show();
          final ModifiableWord word = ModifiableWord.create().setTranslation(translationText)
              .setWord(wordText).setType(ModifiableWord.TYPE_TRAINING).setIsSelected(false);
          wordsAdapter.addWord(word);
          ActivitiesAndFragmentsHelper.hideKeyboard(getActivity());
          dialogInterface.dismiss();
        })
        .setNegativeButton(R.string.cancel, (dialogInterface, i) -> {
          ActivitiesAndFragmentsHelper.hideKeyboard(getActivity());
          dialogInterface.dismiss();
        });
    builder.create().show(); // todo RXJava reactive TextWatcher
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
