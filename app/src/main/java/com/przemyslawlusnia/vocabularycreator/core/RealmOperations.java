package com.przemyslawlusnia.vocabularycreator.core;

import io.realm.RealmModel;

public interface RealmOperations {

  void add(RealmModel realmClass);

  void deleteTable(Class<? extends RealmModel> realmClass);
}
