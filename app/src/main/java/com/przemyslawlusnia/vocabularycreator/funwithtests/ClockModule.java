package com.przemyslawlusnia.vocabularycreator.funwithtests;

import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock;
import com.przemyslawlusnia.vocabularycreator.core.di.AppScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ClockModule {

  @Provides
  @AppScope
  MyClock myClock() {
    return new MyClock();
  }
}
