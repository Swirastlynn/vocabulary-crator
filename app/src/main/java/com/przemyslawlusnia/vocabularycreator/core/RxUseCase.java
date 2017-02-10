package com.przemyslawlusnia.vocabularycreator.core;

import rx.Observable;

public interface RxUseCase<T> {

    Observable<T> execute();
}
