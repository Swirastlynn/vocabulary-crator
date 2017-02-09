package com.przemyslawlusnia.vocabularycreator.data;

import android.util.Log;
import com.przemyslawlusnia.vocabularycreator.core.RealmOperations;
import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmModel;

public abstract class RealmRepository implements RealmOperations { // todo inject it with Dagger

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

  @Override
  public void add(RealmModel realmModel) {
    openRealmIfClosed();
    transaction = realm.executeTransactionAsync(
        realm -> realm.copyToRealmOrUpdate(realmModel),
        this::onSuccessTransaction,
        this::onFailureTransaction);
  }

  private void onSuccessTransaction() {
    Log.d(getClass().getSimpleName(), "Success transaction"); // todo
    closeRealm();
  }

  private void onFailureTransaction(Throwable throwable) {
    // todo
    closeRealm();
  }

  @Override
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