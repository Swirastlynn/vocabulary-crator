package com.przemyslawlusnia.vocabularycreator;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.przemyslawlusnia.vocabularycreator.utils.CommonUtils;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import net.danlew.android.joda.JodaTimeAndroid;

public class VocabularyCreatorApplication extends Application {

  private static final int STETHO_ROWS_LIMIT = 5000;
  private AppComponent appComponent;

  public static VocabularyCreatorApplication get(Context context) {
    return (VocabularyCreatorApplication) context.getApplicationContext();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    initAppComponent();
    initJodaTime();
    initStetho();
  }

  private void initAppComponent() {
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
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

  public AppComponent getAppComponent() {
    return appComponent;
  }

}
