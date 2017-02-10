package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.UseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.GetAllWordsUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRealmRepository;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import javax.inject.Singleton;
import rx.android.schedulers.AndroidSchedulers;

@Module
public class WordsDomainModule {

  @Provides
  @Singleton
  AddWordUseCase addWordUseCase(WordsRealmRepository wordsRealmRepository) { // todo WordsRealmRepository change to abstract interface?
    return new AddWordUseCase(wordsRealmRepository);
  }

  @Provides
  @Singleton
  DeleteWordUseCase deleteWordUseCase(WordsRealmRepository wordsRealmRepository) { // todo WordsRealmRepository change to abstract interface?
    return new DeleteWordUseCase(wordsRealmRepository);
  }

  @Provides
  @Singleton
    // todo what about WordDomainModel? WordRealm? Sure?
  UseCase<List<WordRealm>> getAllWordsUseCase(WordsRealmRepository wordsRealmRepository) { // todo WordsRealmRepository change to abstract interface?
    return new GetAllWordsUseCase(AndroidSchedulers.mainThread(), wordsRealmRepository);
  }

}