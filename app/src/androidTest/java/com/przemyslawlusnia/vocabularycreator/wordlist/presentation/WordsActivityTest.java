package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.WordsViewHolder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class WordsActivityTest {

  public static final String TEST_WORD = "test word";
  public static final String TEST_TRANSLATION = "test translation";

  @Rule
  public ActivityTestRule<WordsActivity> activityRule =
      new ActivityTestRule<WordsActivity>(WordsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
          RealmConfiguration config = new RealmConfiguration.Builder().inMemory().name("test-realm").build();
          Realm.deleteRealm(config);
          Realm.setDefaultConfiguration(config);
        }
      };

  @Test
  public void ensureFragmentContainerIsVisible() {
    //given user opens activity
    //when nothing
    //then
    onView(ViewMatchers.withId(R.id.fragmentContainer)).check(matches(isDisplayed()));
  }

  @Test
  public void showAddWordDialogAndBackPressTwice_wordEditTextNotExists() {
    //given user opens activity
    onView(allOf(withId(R.id.addWordFab), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed()));
    //when
    pressBack();
    pressBack();
    //then
    onView(withId(R.id.wordEditTxt)).check(doesNotExist());
  }

  @Test
  public void showAddWordDialogAndCancel_wordEditTextNotExists() {
    //given user opens activity
    onView(allOf(withId(R.id.addWordFab), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed()));
    //when
    onView(withText(R.string.cancel)).perform(click());
    //then
    onView(withId(R.id.wordEditTxt)).check(doesNotExist());
  }

  @Test
  public void addThenDeleteWord_wordDoesNotExistsInRecyclerView() {
    //given user opens activity and vocabulary list is empty

    //when
    addTestWord(TEST_WORD, TEST_TRANSLATION);
    clickOkButton();
    clickTestWord();
    deleteTestWord();

    //then
    //check on deleted ViewHolder data
    try {
      clickTestWord();
    } catch (NoMatchingViewException e) {
      //ViewHolder data is not in RecyclerView data. It's good.
    }
    //additionally check on view
    try {
      onView(withId(R.id.word))
          .check(matches(not(withText(TEST_WORD))))
          .check(matches(isDisplayed()));
      onView(withId(R.id.translation))
          .check(matches(not(withText(TEST_TRANSLATION))))
          .check(matches(isDisplayed()));
    } catch (NoMatchingViewException e) {
      //View is not in hierarchy. It's good.
    }
  }

  // edit - edited correctly

  // edit possible for single selected

  // edit impossible for none or multi selected

  // delete action possible for single and multi selected

  @Test
  public void emptyVocabularyAndWordNotSelected_ActionsNotExists() {
    //given user opens activity and vocabulary list is empty
    //when nothing
    //then
    onView(withId(R.id.action_delete)).check(doesNotExist());
    onView(withId(R.id.action_edit)).check(doesNotExist());
  }

  @Test
  public void wordSelected_ActionsVisible() {
    //given user opens activity and vocabulary list is empty
    //when
    addTestWord(TEST_WORD, TEST_TRANSLATION);
    clickOkButton();
    clickTestWord();
    //then
    onView(withId(R.id.action_delete)).check(matches(isDisplayed()));
    onView(withId(R.id.action_edit)).check(matches(isDisplayed()));
  }

  @Test
  public void deleteWordAndWordNotSelected_ActionsNotExist() {
    //given
    addTestWord(TEST_WORD, TEST_TRANSLATION);
    clickOkButton();
    clickTestWord();
    //when
    deleteTestWord();
    //then
    onView(withId(R.id.action_delete)).check(doesNotExist());
    onView(withId(R.id.action_edit)).check(doesNotExist());
  }

  @Test
  public void addEmptyWord_NotPossible() {
    //given user opens activity
    //when
    addTestWord("", TEST_TRANSLATION);
    //then
    onView(allOf(withId(android.R.id.button1), withText(R.string.ok))).check(matches(not(isEnabled())));
  }

  // can add empty translation if word is

  // popup - cancel and ok hide

  // popup: 2 x enter == OK click (TDD: feature to add)

  private void clickTestWord() {
    onView(allOf(withId(R.id.wordsRecyclerView), withParent(withId(R.id.cardView)), isDisplayed()))
        .perform(actionOnHolderItem(withWordAndTranslation(TEST_WORD, TEST_TRANSLATION), click()));
  }

  private void addTestWord(String testWord, String testTranslation) {
    onView(allOf(withId(R.id.addWordFab), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed())).perform(replaceText(testWord));
    onView(allOf(withId(R.id.translationEditTxt), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.translationEditTxt), isDisplayed())).perform(replaceText(testTranslation), closeSoftKeyboard());
  }

  private void clickOkButton() {
    onView(allOf(withId(android.R.id.button1), withText("Ok"))).perform(scrollTo(), click());
  }

  private void deleteTestWord() {
    onView(withId(R.id.word))
        .check(matches(withText(TEST_WORD)))
        .check(matches(isDisplayed()));
    onView(withId(R.id.translation))
        .check(matches(withText(TEST_TRANSLATION)))
        .check(matches(isDisplayed()));
    onView(allOf(withId(R.id.action_delete), isDisplayed())).perform(click());
  }

  public static Matcher<RecyclerView.ViewHolder> withWordAndTranslation(String word, String translation) {
    checkNotNull(word);
    checkNotNull(translation);
    return new BoundedMatcher<RecyclerView.ViewHolder, WordsViewHolder>(WordsViewHolder.class) {
      @Override
      protected boolean matchesSafely(WordsViewHolder holder) {
        return holder.wordTxt.getText().toString().equalsIgnoreCase(word)
            && holder.translationTxt.getText().toString().equalsIgnoreCase(translation);
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("view holder with word, translation: " + word + ", " + translation);
      }
    };
  }
}
