package com.przemyslawlusnia.vocabularycreator.core

import android.util.Log
import com.google.firebase.crash.FirebaseCrash
import timber.log.Timber

class FirebaseCrashTree : Timber.Tree() {

    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return !(priority == Log.VERBOSE || priority == Log.INFO || priority == Log.DEBUG)
    }

    override fun log(priority: Int, tag: String, message: String, t: Throwable?) {
        FirebaseCrash.logcat(priority, tag, message)
        if (t != null) {
            FirebaseCrash.report(t)
        }
    }
}
