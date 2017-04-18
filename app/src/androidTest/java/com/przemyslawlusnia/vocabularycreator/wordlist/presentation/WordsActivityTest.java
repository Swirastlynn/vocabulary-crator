package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.wordlist.presentation.viewholder.WordsViewHolder;
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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnHolderItem;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class WordsActivityTest {

  @Rule
  public ActivityTestRule<WordsActivity> activityRule =
      new ActivityTestRule<>(WordsActivity.class);

  @Test
  public void ensureFragmentContainerIsVisible() {
    onView(ViewMatchers.withId(R.id.fragmentContainer)).check(matches(isDisplayed()));
  }

  @Test
  public void addWordClickAndBack_noWordEditText() {
    onView(allOf(withId(R.id.addWordFab), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed()));
    pressBack();
    onView(allOf(withId(R.id.wordEditTxt), not(isDisplayed())));
  }

  // todo click on an item for mocked data.
  // you cannot be dependent on previous tests!


  @Test
  public void addThenDeleteWord_wordDoesNotExistsInRecyclerView() {
    addTestWord();
    onView(allOf(withId(R.id.wordsRecyclerView), withParent(withId(R.id.cardView)), isDisplayed()))
        .perform(actionOnHolderItem(withWordAndTranslation("test word", "test translation"), click()));
    onView(withId(R.id.word))
        .check(matches(withText("test word")))
        .check(matches(isDisplayed()));
    onView(withId(R.id.translation))
        .check(matches(withText("test translation")))
        .check(matches(isDisplayed()));

    onView(allOf(withId(R.id.action_delete), isDisplayed())).perform(click());

    // check on deleted ViewHolder data
    try {
      onView(allOf(withId(R.id.wordsRecyclerView), withParent(withId(R.id.cardView)), isDisplayed()))
          .perform(actionOnHolderItem(withWordAndTranslation("test word", "test translation"), click()));
    } catch (NoMatchingViewException e) {
      // View is not in hierarchy
    }
    // check on view - just for training
    try {
      onView(withId(R.id.word))
          .check(matches(not(withText("test word"))))
          .check(matches(isDisplayed()));
      onView(withId(R.id.translation))
          .check(matches(not(withText("test translation"))))
          .check(matches(isDisplayed()));
    } catch (NoMatchingViewException e) {
      // View is not in hierarchy
    }
  }

  private void addTestWord() {
    onView(allOf(withId(R.id.addWordFab), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.wordEditTxt), isDisplayed())).perform(replaceText("test word"));
    onView(allOf(withId(R.id.translationEditTxt), isDisplayed())).perform(click());
    onView(allOf(withId(R.id.translationEditTxt), isDisplayed())).perform(replaceText("test translation"), closeSoftKeyboard());
    onView(allOf(withId(android.R.id.button1), withText("Ok"))).perform(scrollTo(), click());
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

  // edit - edited correctly

  // edit possible for single selected

  // edit impossible for none or multi selected

  // delete action possible for single and multi selected

  // delete action impossible for none selected

  // cannot add empty word

  // can add empty translation if word is

  // popup - cancel and ok hide

  // popup: 2 x enter == OK click (TDD: feature to add)
}