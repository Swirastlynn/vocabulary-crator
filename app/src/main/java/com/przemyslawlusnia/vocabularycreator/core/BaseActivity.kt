package com.przemyslawlusnia.vocabularycreator.core

import android.support.annotation.ColorInt
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.przemyslawlusnia.vocabularycreator.R
import com.przemyslawlusnia.vocabularycreator.core.utils.ObjectUtils

abstract class BaseActivity : AppCompatActivity() {

    private val TAG = BaseActivity::class.java.simpleName

    protected var toolbar: Toolbar? = null

    protected fun setupToolbar() {
        if (ObjectUtils.isNotNull<Toolbar>(TAG, "setupToolbar", toolbar)) {
            setSupportActionBar(toolbar)
            setToolbarTitle(getString(R.string.app_name))
        }
    }

    protected fun setToolbarTitle(title: CharSequence) {
        if (ObjectUtils.isNotNull<Toolbar>(TAG, "setToolbarTitle", toolbar)) {
            toolbar?.title = title
        }
    }

    protected fun setToolbarColor(@ColorInt color: Int) {
        if (ObjectUtils.isNotNull<Toolbar>(TAG, "setToolbarColor", toolbar)) {
            toolbar?.setBackgroundColor(color)
        }
    }

}
