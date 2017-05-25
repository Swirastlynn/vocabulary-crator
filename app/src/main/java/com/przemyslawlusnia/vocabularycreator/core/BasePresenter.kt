package com.przemyslawlusnia.vocabularycreator.core

abstract class BasePresenter<V> {

    protected var view: V? = null

    fun onAttachView(frag: V) {
        view = frag
    }

    fun onDetach() {
        view = null
    }

    abstract fun onUnsubscribe()

}
