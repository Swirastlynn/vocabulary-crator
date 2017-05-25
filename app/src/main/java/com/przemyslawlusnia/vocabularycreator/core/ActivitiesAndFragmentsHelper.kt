package com.przemyslawlusnia.vocabularycreator.core

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

object ActivitiesAndFragmentsHelper {

    fun showFragment(activity: BaseActivity, fragment: Fragment, containerId: Int) {
        activity.supportFragmentManager
                .beginTransaction()
                .replace(containerId, fragment)
                .commit()
    }

    fun showFragmentAndAddToBackstack(activity: BaseActivity, fragment: Fragment, containerId: Int) {
        activity.supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(containerId, fragment)
                .commit()
    }

    fun clearBackStack(activity: AppCompatActivity) {
        val manager = activity.supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    fun hideKeyboard(activity: Activity?) {
        if (activity == null) {
            return
        }

        val focusedView = activity.currentFocus
        if (focusedView != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
        }
        activity.window.setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
        )
    }

    fun showKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

}
