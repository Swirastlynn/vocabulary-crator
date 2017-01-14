package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
      MenuItem item = menu.findItem(R.id.action_edit);
      item.setEnabled(false);
      ViewUtils.tintMenuItemIcon(R.color.gray, item);
    } else if (menuMode == MenuMode.MultipleSelection) {
      inflater.inflate(R.menu.words_selection_menu, menu);
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
        Toast.makeText(getContext(), "Action Delete", Toast.LENGTH_SHORT).show();
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

  private List<Word> getTempWordList() {
    List<Word> list = new ArrayList<>();
    list.add(Word.create("obliterate", "wymazać", Word.TYPE_TRAINING));
    list.add(Word.create("surreptitious", "dyskretny", Word.TYPE_TRAINING));
    list.add(Word.create("new", "nowy", Word.TYPE_TRAINING));
    list.add(Word.create("old", "stary", Word.TYPE_TRAINING));
    list.add(Word.create("old Brohacz", "stary snowboardzista", Word.TYPE_TRAINING));
    list.add(Word.create("menacingly", "groźnie", Word.TYPE_TRAINING));
    list.add(Word.create("hush", "cicho-sza", Word.TYPE_TRAINING));
    list.add(Word.create("hush!", "cyt!", Word.TYPE_TRAINING));
    list.add(Word.create("exert", "wyciąg", Word.TYPE_TRAINING));
    list.add(Word.create("assure", "zapewniać, ubezpieczać", Word.TYPE_TRAINING));
    list.add(Word.create("assert", "zapewniać", Word.TYPE_TRAINING));
    list.add(Word.create("assess", "oszacować", Word.TYPE_TRAINING));
    list.add(Word.create("assessment", "oszacowanie", Word.TYPE_TRAINING));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("swivel", "obracać się", Word.TYPE_LEARNED));
    list.add(Word.create("assess", "oszacować", Word.TYPE_TRAINING));
    list.add(Word.create("assessment", "oszacowanie", Word.TYPE_TRAINING));
    return list;
  }

  @OnClick(R.id.addWordFab)
  public void fabClick(View view) {
    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null)
        .show();
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
