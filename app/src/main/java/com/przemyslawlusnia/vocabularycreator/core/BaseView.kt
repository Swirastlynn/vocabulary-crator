package com.przemyslawlusnia.vocabularycreator.core

interface BaseView {
    fun showProgress()

    fun hideProgress()

    fun showFailure(message: String?)
}
