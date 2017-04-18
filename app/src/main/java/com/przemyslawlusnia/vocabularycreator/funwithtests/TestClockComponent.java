package com.przemyslawlusnia.vocabularycreator.funwithtests;

import com.przemyslawlusnia.vocabularycreator.core.di.AppScope;
import dagger.Component;

@AppScope
@Component(modules = {TestClockModule.class})
public interface TestClockComponent extends ClockComponent {

}
