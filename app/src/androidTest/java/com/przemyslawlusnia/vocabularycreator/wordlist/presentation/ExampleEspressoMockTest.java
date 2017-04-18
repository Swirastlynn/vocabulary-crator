package com.przemyslawlusnia.vocabularycreator.wordlist.presentation;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.przemyslawlusnia.vocabularycreator.R;
import com.przemyslawlusnia.vocabularycreator.core.VocabularyCreatorApplication;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@RunWith(AndroidJUnit4.class)
public class ExampleEspressoMockTest {

  @Rule
  public ActivityTestRule<WordsActivity> activityRule =
      new ActivityTestRule<WordsActivity>(WordsActivity.class) {
        @Override
        protected void beforeActivityLaunched() {
          VocabularyCreatorApplication application = (VocabularyCreatorApplication)
              InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
          application.setTestClockComponent();
        }
      };


  @Test
  void stupidButEspressoTest() {
    // todo create new activity for test purposes
  }
  // todo move test components here and call them mock?

  // todo use with espresso test and normal test - maybe test components should be inside main code to handle both cases?

}
