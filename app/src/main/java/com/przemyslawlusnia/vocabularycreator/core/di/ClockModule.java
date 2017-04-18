package com.przemyslawlusnia.vocabularycreator.core.di;

import com.przemyslawlusnia.vocabularycreator.core.clock.MyClock;
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
