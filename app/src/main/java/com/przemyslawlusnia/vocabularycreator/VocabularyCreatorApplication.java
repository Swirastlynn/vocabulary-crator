package com.przemyslawlusnia.vocabularycreator;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import net.danlew.android.joda.JodaTimeAndroid;

public class VocabularyCreatorApplication extends Application {

  private static final int STETHO_ROWS_LIMIT = 5000;

  @Override
  public void onCreate() {
    super.onCreate();
    initJodaTime();
    initStetho();
  }

  protected void initJodaTime() {
    JodaTimeAndroid.init(this);
  }

  private void initStetho() {
    if (CommonUtils.isDebug()) {
      Stetho.initialize(
          Stetho.newInitializerBuilder(this)
              .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
              .enableWebKitInspector(RealmInspectorModulesProvider
                  .builder(this)
                  .withLimit(STETHO_ROWS_LIMIT)
                  .build())
              .build());
    }
  }

}
