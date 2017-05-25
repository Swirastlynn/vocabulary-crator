package com.przemyslawlusnia.vocabularycreator.core

import android.app.Application
import android.support.annotation.VisibleForTesting
import com.facebook.stetho.Stetho
import com.przemyslawlusnia.vocabularycreator.BuildConfig
import com.przemyslawlusnia.vocabularycreator.core.di.AppComponent
import com.przemyslawlusnia.vocabularycreator.core.di.AppModule
import com.przemyslawlusnia.vocabularycreator.core.di.DaggerAppComponent
import com.przemyslawlusnia.vocabularycreator.core.utils.CommonUtils
import com.przemyslawlusnia.vocabularycreator.funwithtests.ClockComponent
import com.przemyslawlusnia.vocabularycreator.funwithtests.DaggerClockComponent
import com.przemyslawlusnia.vocabularycreator.funwithtests.DaggerTestClockComponent
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class VocabularyCreatorApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initTimber()
        initAppComponent()
        initClockComponent()
        initRealm()
        initStetho()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return super.createStackElementTag(element) + ":" + element.lineNumber
                }
            })
        } else {
            Timber.plant(FirebaseCrashTree())
        }
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        appComponent.inject(this)
    }

    private fun initClockComponent() {
        clockComponent = DaggerClockComponent.builder().build()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .name(BuildConfig.REALM_DATABASE_NAME)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    private fun initStetho() {
        if (CommonUtils.isDebug()) {
            Stetho.initialize(
                    Stetho.newInitializerBuilder(this)
                            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                            .enableWebKitInspector(RealmInspectorModulesProvider
                                    .builder(this)
                                    .withLimit(STETHO_ROWS_LIMIT.toLong())
                                    .build())
                            .build())
        }
    }

    companion object {

        private val STETHO_ROWS_LIMIT = 5000
        lateinit var appComponent: AppComponent
            private set
        lateinit var clockComponent: ClockComponent
            private set

        @VisibleForTesting
        fun setTestClockComponent() {
            clockComponent = DaggerTestClockComponent.builder().build()
        }
    }
}
