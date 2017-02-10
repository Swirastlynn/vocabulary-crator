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
import rx.android.schedulers.AndroidSchedulers;

@Module
public class WordsDomainModule {

  @Provides
  @WordsDomainScope
  AddWordUseCase addWordUseCase(WordsRealmRepository wordsRealmRepository) { // todo WordsRealmRepository change to abstract interface?
    return new AddWordUseCase(wordsRealmRepository);
  }

  @Provides
  @WordsDomainScope
  DeleteWordUseCase deleteWordUseCase(WordsRealmRepository wordsRealmRepository) { // todo WordsRealmRepository change to abstract interface?
    return new DeleteWordUseCase(wordsRealmRepository);
  }

  @Provides
  @WordsDomainScope
    // todo what about WordDomainModel? WordRealm? Sure?
  UseCase<List<WordRealm>> getAllWordsUseCase(WordsRealmRepository wordsRealmRepository) { // todo WordsRealmRepository change to abstract interface?
    return new GetAllWordsUseCase(AndroidSchedulers.mainThread(), wordsRealmRepository);
  }

}