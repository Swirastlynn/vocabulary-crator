package com.przemyslawlusnia.vocabularycreator.core.di;

import com.przemyslawlusnia.vocabularycreator.core.clock.TestMyClock;
import dagger.Module;
import dagger.Provides;

@Module
public class TestClockModule {

  @Provides
  @AppScope
  TestMyClock testMyClock() {
    return new TestMyClock();
  }

}
