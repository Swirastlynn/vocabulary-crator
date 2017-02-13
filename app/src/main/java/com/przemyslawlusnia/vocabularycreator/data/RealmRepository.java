package com.przemyslawlusnia.vocabularycreator.data;

import android.util.Log;
import io.realm.Realm;
import io.realm.RealmModel;

public abstract class RealmRepository {

  private static final String TAG = RealmRepository.class.getSimpleName();

  protected Realm realm;

  public RealmRepository() {
    this.realm = Realm.getDefaultInstance();
  }

  protected void openRealmIfClosed() {
    if (realm != null && realm.isClosed()) {
      realm = Realm.getDefaultInstance();
    }
  }

  public void deleteTable(Class<? extends RealmModel> realmClass) {
    openRealmIfClosed();
    realm.delete(realmClass);
  }

  protected void closeRealm() {
    if (realm != null && !realm.isClosed()) {
      realm.close();
    } else {
      Log.e(TAG, "Unsuccessful Realm close.");
    }
  }
}