package saauan.ramenpartycompanion

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PreResultInstrumentedTest {

    @Test
    fun theMultiplierIsInitiallyZero() {
        launchFragmentInContainer<PreResultFragment>()

        onView(withId(R.id.score_multiplier)).check(matches(withText("0")))
    }

    @Test
    fun whenClickingThePlusButton_IncrementsMultiplier() {
        launchFragmentInContainer<PreResultFragment>()

        onView(withId(R.id.pre_result_plus_button)).perform(click())
        onView(withId(R.id.score_multiplier)).check(matches(withText("1")))
    }

    @Test
    fun whenClickingTheMinusButton_DecrementsMultiplier() {
        launchFragmentInContainer<PreResultFragment>()

        onView(withId(R.id.pre_result_minus_button)).perform(click())
        onView(withId(R.id.score_multiplier)).check(matches(withText("-1")))
    }
}