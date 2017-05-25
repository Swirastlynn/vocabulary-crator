package com.przemyslawlusnia.vocabularycreator.core

import android.content.Context
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v4.app.Fragment
import android.util.Log

open class BaseFragment : Fragment() {

    /**
     * There are two types of requests: on demand (click) and on enter screen.
     *
     *
     * On demand: use [BaseFragment.networking] to keep info if request has started.
     * If config changes (screen rotation, language change, keyboard change etc...) you will be able to retrieve it
     * with savedInstanceState and restart from onResume() if mentioned value is true.
     *
     *
     * On enter screen: call request from onCreate or onResume (which you prefer for your case)
     */
    protected var networking = false
    protected var eventListener: EventListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is EventListener) {
            eventListener = context
        } else {
            Log.d(TAG, "onAttach, context is not EventListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networking = savedInstanceState?.getBoolean(NETWORKING_STATE) ?: false
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(NETWORKING_STATE, networking)
    }

    protected fun setToolbarTitle(txt: String) {
        eventListener?.setToolbarTitleTxt(txt)
    }

    protected fun setToolbarBackground(@ColorRes colorRes: Int) {
        eventListener?.setToolbarBackground(colorRes)
    }

    protected fun setToolbarShadow(visible: Boolean) {
        eventListener?.setToolbarShadow(visible)
    }

    protected fun checkListenerClass(context: Context, expectedContextClass: Class<*>) {
        if (!expectedContextClass.isAssignableFrom(context.javaClass)) {
            throw RuntimeException(context.toString() + " must implement " + expectedContextClass.simpleName)
        }
    }

    override fun onDetach() {
        eventListener = null
        super.onDetach()
    }

    interface EventListener {
        fun setToolbarTitleTxt(txt: String)

        fun setToolbarBackground(@ColorRes colorRes: Int)

        fun setToolbarShadow(visible: Boolean)
    }

    companion object {
        private val TAG = BaseFragment::class.java.simpleName
        protected val NETWORKING_STATE = "NETWORKING_STATE"
    }

}
