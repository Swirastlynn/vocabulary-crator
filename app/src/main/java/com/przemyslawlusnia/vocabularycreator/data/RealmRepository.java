package com.przemyslawlusnia.vocabularycreator.data;

import android.util.Log;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmModel;

public abstract class RealmRepository {

  private static final String TAG = RealmRepository.class.getSimpleName();

  protected Realm realm;
  protected RealmAsyncTask transaction;

  public RealmRepository() {
    this.realm = Realm.getDefaultInstance();
  }

  protected void openRealmIfClosed() {
    if (realm != null && realm.isClosed()) {
      realm = Realm.getDefaultInstance();
    }
  }

  protected void onSuccessTransaction() {
    Log.d(getClass().getSimpleName(), "Success transaction"); // todo
    closeRealm();
  }

  protected void onFailureTransaction(Throwable throwable) {
    // todo
    closeRealm();
  }

  public void deleteTable(Class<? extends RealmModel> realmClass) {
    openRealmIfClosed();
    realm.delete(realmClass);
  }

  protected void closeRealm() {
    if (transaction != null && !transaction.isCancelled()) {
      transaction.cancel();
    } else {
      Log.e(TAG, "Unsuccessful transaction cancellation.");
    }
    if (realm != null && !realm.isClosed()) {
      realm.close();
    } else {
      Log.e(TAG, "Unsuccessful Realm close.");
    }
  }
}