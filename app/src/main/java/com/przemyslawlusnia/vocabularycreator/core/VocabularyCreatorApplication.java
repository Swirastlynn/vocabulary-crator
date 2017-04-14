package com.przemyslawlusnia.vocabularycreator.core;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.przemyslawlusnia.vocabularycreator.BuildConfig;
import com.przemyslawlusnia.vocabularycreator.core.di.AppComponent;
import com.przemyslawlusnia.vocabularycreator.core.di.AppModule;
import com.przemyslawlusnia.vocabularycreator.core.di.DaggerAppComponent;
import com.przemyslawlusnia.vocabularycreator.core.utils.CommonUtils;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class VocabularyCreatorApplication extends Application {

  private static final int STETHO_ROWS_LIMIT = 5000;
  private static AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    initTimber();
    initAppComponent();
    initRealm();
    initStetho();
  }

  private void initTimber() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree() {
        @Override
        protected String createStackElementTag(StackTraceElement element) {
          return super.createStackElementTag(element) + ":" + element.getLineNumber();
        }
      });
    } else {
      Timber.plant(new FirebaseCrashTree());
    }
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

  public static AppComponent getAppComponent() {
    return appComponent;
  }

}
