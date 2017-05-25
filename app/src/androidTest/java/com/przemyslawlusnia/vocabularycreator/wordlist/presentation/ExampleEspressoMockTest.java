package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication;
import com.przemyslawlusnia.vocabularycreator.core.clock.TestMyClock;
import com.przemyslawlusnia.vocabularycreator.funwithtests.TestActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class ExampleEspressoMockTest {

  @Rule
  public ActivityTestRule<TestActivity> activityRule =
      new ActivityTestRule<TestActivity>(TestActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
          VocabularyCreatorApplication.Companion.setTestClockComponent();
        }
      };

  @Test
  public void testTxt_hasAppropriateValue() {
    onView(withText(TestMyClock.Companion.getTEST_DATE())).check(matches(isDisplayed()));
  }
  // todo use with espresso test and normal test IF IT IS GOOD PRACTICE?
  // todo Maybe test components should be inside main code to handle both cases?

}
