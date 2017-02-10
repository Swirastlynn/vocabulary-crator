package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.UseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import dagger.Subcomponent;
import java.util.List;
import javax.inject.Singleton;

@Singleton
@Subcomponent(modules = WordsDomainModule.class)
public interface WordsDomainComponent {

  AddWordUseCase addWordUseCase();
  DeleteWordUseCase deleteWordUseCase();
  UseCase<List<WordRealm>> getAllWordsUseCase();

}