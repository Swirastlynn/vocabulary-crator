package com.przemyslawlusnia.vocabularycreator.core;

import android.app.Application;
import android.content.Context;
import com.facebook.stetho.Stetho;
import com.przemyslawlusnia.vocabularycreator.BuildConfig;
import com.przemyslawlusnia.vocabularycreator.core.di.AppComponent;
import com.przemyslawlusnia.vocabularycreator.core.di.AppModule;
import com.przemyslawlusnia.vocabularycreator.core.di.DaggerAppComponent;
import com.przemyslawlusnia.vocabularycreator.core.utils.CommonUtils;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import io.realm.Realm;
import io.realm.RealmConfiguration;

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
    initRealm();
    initStetho();
  }

  private void initAppComponent() {
    appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    appComponent.inject(this);
  }

  private void initRealm() {
    Realm.init(this);
    RealmConfiguration realmConfig = new RealmConfiguration.Builder()
        .name(BuildConfig.REALM_DATABASE_NAME)
        .deleteRealmIfMigrationNeeded()
        .build();
    Realm.setDefaultConfiguration(realmConfig);
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
