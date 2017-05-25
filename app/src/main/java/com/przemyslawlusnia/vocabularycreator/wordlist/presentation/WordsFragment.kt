package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.core.ActivitiesAndFragmentsHelper
import com.przemyslawlusnia.vocabularycreator.core.BaseFragment
import com.przemyslawlusnia.vocabularycreator.core.Constants
import com.przemyslawlusnia.vocabularycreator.core.utils.ViewUtils
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsDomainComponentProviderSingleton
import com.przemyslawlusnia.vocabularycreator.wordlist.di.WordsViewModule
import timber.log.Timber
import javax.inject.Inject

class WordsFragment : BaseFragment(), WordsView, OnWordsSelectionListener {

    lateinit var wordsRecyclerView: RecyclerView
    lateinit var fab: FloatingActionButton

    @Inject
    lateinit var presenter: WordsPresenter
    @Inject
    lateinit var wordsAdapter: WordsAdapter
    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    private var menuMode: MenuMode = MenuMode.NoSelection

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        setupDependencies()
        presenter.onAttachView(this)
    }

    private fun setupDependencies() {
        WordsDomainComponentProviderSingleton.component()
                .plus(WordsViewModule(this, this, activity as WordsActivity))
                .inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (menuMode == MenuMode.SingleSelection) {
            inflater?.inflate(R.menu.words_selection_menu, menu)
        } else if (menuMode == MenuMode.MultipleSelection) {
            inflater?.inflate(R.menu.words_selection_menu, menu)
            val actionEditMenuItem: MenuItem? = menu?.findItem(R.id.action_edit)
            actionEditMenuItem?.isEnabled = false
            ViewUtils.tintMenuItemIcon(R.color.gray, actionEditMenuItem)
        } else {
            inflater?.inflate(R.menu.words_start_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_search -> {
                Toast.makeText(context, "Action Search", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_settings -> {
                Toast.makeText(context, "Action Settings", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_learn -> {
                Toast.makeText(context, "Action Learn", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_train -> {
                Toast.makeText(context, "Action Train", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_edit -> {
                buildAndShowAddWordDialog(wordsAdapter.selectedWord, true)
                return true
            }
            R.id.action_delete -> {
                presenter.deleteWords(wordsAdapter.selectedWords)
                return true
            }
            else -> {
                Timber.e(TAG, "No such action")
            }
        }

        return true
    }

    override fun updateNoSelection() {
        if (menuMode != MenuMode.NoSelection) {
            menuMode = MenuMode.NoSelection
            activity.supportInvalidateOptionsMenu()
        }
    }

    override fun updateSingleSelection() {
        menuMode = MenuMode.SingleSelection
        activity.supportInvalidateOptionsMenu()
    }

    override fun updateMultipleSelection() {
        if (menuMode != MenuMode.MultipleSelection) {
            menuMode = MenuMode.MultipleSelection
            activity.supportInvalidateOptionsMenu()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.words_fragment, container, false)
        wordsRecyclerView = view.findViewById(R.id.wordsRecyclerView) as RecyclerView
        fab = view.findViewById(R.id.addWordFab) as FloatingActionButton
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener { v: View ->
            buildAndShowAddWordDialog(WordViewModel("", "", Constants.TYPE_TRAINING, false), false)
        }
        wordsRecyclerView.layoutManager = linearLayoutManager
        presenter.getAllWords()
        wordsRecyclerView.adapter = wordsAdapter
    }

    private fun buildAndShowAddWordDialog(oldWord: WordViewModel, edit: Boolean) {
        val wordDialogWrapper = WordDialogWrapper(context)
        wordDialogWrapper.buildAndShow(if (edit) R.string.edit_word else R.string.new_word,
                object : WordDialogListener {
                    override fun positiveButtonClick(newWord: WordViewModel) {
                        if (edit) {
                            wordsAdapter.editSelectedWord(newWord)
                        } else {
                            presenter.addWord(newWord)
                        }
                        ActivitiesAndFragmentsHelper.hideKeyboard(activity)
                    }

                    override fun negativeButtonClick() {
                        ActivitiesAndFragmentsHelper.hideKeyboard(activity)
                    }
                }, oldWord
        )
        // todo RXJava reactive TextWatcher
        ActivitiesAndFragmentsHelper.showKeyboard(activity)
    }

    override fun showAddWord(wordViewModel: WordViewModel) {
        wordsAdapter.addWord(wordViewModel)
    }

    override fun showAllWordsAfterDeletion() {
        wordsAdapter.clearSelection()
        presenter.getAllWords()
    }

    override fun showAllWords(wordViewModels: List<WordViewModel>) {
        wordsAdapter.setWords(wordViewModels)
    }

    override fun showProgress() {
        // not used todo progressBar
    }

    override fun hideProgress() {
        // not used
    }

    override fun showFailure(message: String?) {
        // not used todo log
    }

    override fun onPause() {
        presenter.onUnsubscribe()
        super.onPause()
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    companion object {
        private val TAG = WordsFragment::class.java.simpleName

        fun newInstance(): WordsFragment {
            return WordsFragment()
        }
    }
}
