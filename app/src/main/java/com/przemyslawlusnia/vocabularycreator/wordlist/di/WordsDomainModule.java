package com.przemyslawlusnia.vocabularycreator.wordlist.di;

import com.przemyslawlusnia.vocabularycreator.core.RxUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.AddWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.DeleteWordUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.GetAllWordsUseCase;
import com.przemyslawlusnia.vocabularycreator.wordlist.domain.WordDomainModel;
import com.przemyslawlusnia.vocabularycreator.wordlist.repository.WordsRepository;
import dagger.Module;
import dagger.Provides;
import java.util.List;
import rx.schedulers.Schedulers;

@Module
public class WordsDomainModule {

  @Provides
  @WordsDomainScope
  AddWordUseCase addWordUseCase(WordsRepository wordsRepository) {
    return new AddWordUseCase(Schedulers.io(), wordsRepository);
  }

  @Provides
  @WordsDomainScope
  DeleteWordUseCase deleteWordUseCase(WordsRepository wordsRepository) {
    return new DeleteWordUseCase(wordsRepository);
  }

  @Provides
  @WordsDomainScope
  RxUseCase<List<WordDomainModel>> getAllWordsUseCase(WordsRepository wordsRepository) {
    return new GetAllWordsUseCase(Schedulers.io(), wordsRepository);
  }

}