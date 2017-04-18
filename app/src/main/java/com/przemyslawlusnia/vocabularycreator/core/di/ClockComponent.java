package com.przemyslawlusnia.vocabularycreator.core.di;

import android.app.Application;
import dagger.Component;

@AppScope
@Component(modules = {ClockModule.class})
public interface ClockComponent {

  void inject(Application app);

}
