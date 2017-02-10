package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.di.AppComponent;
import dagger.Component;

/**
 * If I had this Component kept in reference, then I would be responsible for its lifecycle -> it should be released
 * wordsDomainComponent = null
 * Check: http://frogermcs.github.io/dependency-injection-with-dagger-2-custom-scopes/
 * <p>
 * In such case I should use custom scope.
 */
@WordsDomainScope
@Component(modules = WordsDomainModule.class, dependencies = AppComponent.class)
public interface WordsDomainComponent {

  WordsViewComponent plus(WordsViewModule wordsViewModule);

}