package com.przemyslawlusnia.vocabularycreator.funwithtests;

import com.przemyslawlusnia.vocabularycreator.core.di.AppScope;
import dagger.Component;

@AppScope
@Component(modules = {ClockModule.class})
public interface ClockComponent {

  void inject(TestActivity testActivity);

}
