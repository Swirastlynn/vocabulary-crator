package com.przemyslawlusnia.vocabularycreator.core;

import io.realm.RealmModel;

public interface RealmOperations { // todo remove?

  void add(RealmModel realmClass);

  void deleteTable(Class<? extends RealmModel> realmClass);
}
