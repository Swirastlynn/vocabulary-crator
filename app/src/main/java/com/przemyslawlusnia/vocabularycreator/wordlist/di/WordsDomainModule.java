package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.GetAllWordsUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordRealm;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import rx.android.schedulers.AndroidSchedulers;

@Module
public class WordsDomainModule {

  @Provides
  @WordsDomainScope
  AddWordUseCase addWordUseCase(WordsRepository wordsRepository) {
    return new AddWordUseCase(wordsRepository);
  }

  @Provides
  @WordsDomainScope
  DeleteWordUseCase deleteWordUseCase(WordsRepository wordsRepository) {
    return new DeleteWordUseCase(wordsRepository);
  }

  @Provides
  @WordsDomainScope
    // todo what about WordDomainModel? WordRealm? Sure?
  RxUseCase<List<WordRealm>> getAllWordsUseCase(WordsRepository wordsRepository) {
    return new GetAllWordsUseCase(AndroidSchedulers.mainThread(), wordsRepository);
  }

}