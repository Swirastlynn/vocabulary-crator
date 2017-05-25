package com.przemyslawlusnia.vocabularycreator.funwithtests

import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock
import com.przemyslawlusnia.vocabularycreator.core.clock.TestMyClock
import com.przemyslawlusnia.vocabularycreator.core.di.AppScope
import dagger.Module
import dagger.Provides

@Module
class TestClockModule { // todo or maybe use @Qualifier and put it into normal module?

    @Provides
    @AppScope
    internal fun myClock(): MyClock {
        return TestMyClock()
    }

}
