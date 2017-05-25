package com.przemyslawlusnia.vocabularycreator.funwithtests

import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock
import com.przemyslawlusnia.vocabularycreator.core.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class ClockModule {

    @Provides
    @AppScope
    internal fun myClock(): MyClock {
        return MyClock()
    }
}
