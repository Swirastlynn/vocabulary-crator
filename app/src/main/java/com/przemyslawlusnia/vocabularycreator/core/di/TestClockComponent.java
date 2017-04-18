package com.przemyslawlusnia.vocabularycreator.core.di;

import dagger.Component;

@AppScope
@Component(modules = {TestClockModule.class})
public interface TestClockComponent extends ClockComponent {

}
