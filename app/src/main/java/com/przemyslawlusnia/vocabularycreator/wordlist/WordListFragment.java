package com.przemyslawlusnia.vocabularycreator.wordlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.przemyslawlusnia.vocabularycreator.BaseFragment;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.VocabularyCreatorApplication;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class WordListFragment extends BaseFragment implements WordListView {

  @BindView(R.id.wordsRecyclerView)
  RecyclerView wordsRecyclerView;

  @Inject
  WordListPresenter presenter;

  public static WordListFragment newInstance() {
    return new WordListFragment();
  }

  public WordListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActivityComponent();
    presenter.onAttachView(this);
  }

  private void setupActivityComponent() {
    VocabularyCreatorApplication.get(getActivity())
        .getAppComponent()
        .plus(new WordListFragmentModule(this))
        .inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.word_list_fragment, container, false);
    butterknifeUnbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    WordListAdapter adapter = new WordListAdapter(getTempWordList());
    wordsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    wordsRecyclerView.setAdapter(adapter);
  }

  private List<Pair<String, String>> getTempWordList() {
    List<Pair<String, String>> list = new ArrayList<>();
    list.add(new Pair<>("new", "nowy"));
    list.add(new Pair<>("old", "stary"));
    list.add(new Pair<>("old Brohacz", "stary snowboardzista"));
    list.add(new Pair<>("menacingly", "groźnie"));
    list.add(new Pair<>("hush", "cicho-sza"));
    list.add(new Pair<>("hush!", "cyt!"));
    list.add(new Pair<>("exert", "wyciąg"));
    list.add(new Pair<>("assure", "zapewniać, ubezpieczać"));
    list.add(new Pair<>("assert", "zapewniać"));
    list.add(new Pair<>("assess", "oszacować"));
    list.add(new Pair<>("assessment", "oszacowanie"));
    list.add(new Pair<>("swivel", "obracać się"));
    return list;
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
