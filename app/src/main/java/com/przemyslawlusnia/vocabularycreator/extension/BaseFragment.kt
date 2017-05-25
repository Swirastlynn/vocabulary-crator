package com.przemyslawlusnia.vocabularycreator.extension

import android.widget.Toast
import com.przemyslawlusnia.vocabularycreator.core.BaseFragment

fun BaseFragment.showMessage(message: String) {
    Toast.makeText(activity,
            message,
            Toast.LENGTH_SHORT).show()
}