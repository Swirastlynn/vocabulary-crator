package com.przemyslawlusnia.vocabularycreator.funwithtests

import com.przemyslawlusnia.vocabularycreator.core.di.AppScope
import dagger.Component

@AppScope
@Component(modules = arrayOf(TestClockModule::class))
interface TestClockComponent : ClockComponent
