package com.przemyslawlusnia.vocabularycreator.extension;

import android.app.Activity
import android.support.annotation.IdRes
import android.view.View

/**
 * LazyThreadSafetyMode.NONE because
 * lazy is actually thread safe by default to avoid that the lambda gets computed more than once.
 * We don’t need that in our scenario, since the bind function will be called only by the main thread.
 */
fun <T : View> Activity.bind(@IdRes res: Int): Lazy<T> {
    @Suppress("UNCHECKED_CAST")
    return lazy(LazyThreadSafetyMode.NONE) { findViewById(res) as T }
}