package com.przemyslawlusnia.vocabularycreator.wordlist.presentation

import android.text.Editable
import android.text.TextWatcher

abstract class AfterTextChangeWatcher : TextWatcher {

    abstract override fun afterTextChanged(s: Editable?)

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // nothing
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // nothing
    }

}