package com.przemyslawlusnia.vocabularycreator.core;

import rx.Observable;

public interface UseCase<T> {

    Observable<T> execute();
}
