package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import android.os.Bundle
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.core.ActivitiesAndFragmentsHelper
import com.przemyslawlusnia.vocabularycreator.core.BaseActivity

class WordsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.words_activity)
        setupToolbar()
        if (savedInstanceState == null) {
            ActivitiesAndFragmentsHelper.showFragment(this, WordsFragment.newInstance(), R.id.fragmentContainer)
        }
    }

}
