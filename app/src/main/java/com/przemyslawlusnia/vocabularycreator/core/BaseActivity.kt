package com.przemyslawlusnia.vocabularycreator.core

import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.extension.bind

abstract class BaseActivity : AppCompatActivity() {

    protected val toolbar: Toolbar by bind(R.id.toolbar)

    protected fun setupToolbar() {
        setSupportActionBar(toolbar)
        setToolbarTitle(getString(R.string.app_name))
    }

    protected fun setToolbarTitle(title: CharSequence) {
        toolbar.title = title
    }

    protected fun setToolbarColor(@ColorInt color: Int) {
        toolbar.setBackgroundColor(color)
    }

}