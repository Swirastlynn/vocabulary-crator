package com.przemyslawlusnia.vocabularycreator.extension

import android.support.annotation.IdRes
import android.view.View

fun <T : View> View.bind(@IdRes res: Int): T {
    @Suppress("UNCHECKED_CAST")
    return this.findViewById(res) as T
}