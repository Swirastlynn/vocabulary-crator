package com.przemyslawlusnia.vocabularycreator.funwithtests

import com.przemyslawlusnia.vocabularycreator.core.di.AppScope
import dagger.Component

@AppScope
@Component(modules = arrayOf(ClockModule::class))
interface ClockComponent {

    fun inject(testActivity: TestActivity)

}
