package saauan.ramenpartycompanion

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayerButtonInstrumentedTest {
    @Test
    fun thePlayerButtonIsDisplayed() {
        val fragmentArgs = bundleOf("player" to "Jean")
        launchFragmentInContainer<PlayerButton>(fragmentArgs)
        onView(withId(R.id.player_button)).check(matches(isDisplayed()))
        onView(withId(R.id.player_button)).check(matches(withText("Jean")))
    }

    @Test
    fun theScoreIsInitiallyZero() {
        val fragmentArgs = bundleOf("player" to "Jean")
        launchFragmentInContainer<PlayerButton>(fragmentArgs)

        onView(withId(R.id.current_score)).check(matches(withText("000")))
    }

    @Test
    fun whenClickingThePlayerButton_IncrementsScore() {
        val fragmentArgs = bundleOf("player" to "Jean")
        launchFragmentInContainer<PlayerButton>(fragmentArgs)

        onView(withId(R.id.player_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("001")))
        onView(withId(R.id.player_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("002")))
    }

    @Test
    fun whenClickingTheMinusButton_DecrementScore() {
        val fragmentArgs = bundleOf("player" to "Jean")
        launchFragmentInContainer<PlayerButton>(fragmentArgs)

        onView(withId(R.id.player_button)).perform(click())
        onView(withId(R.id.player_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("002")))
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("001")))
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("000")))
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("000"))) // Does not go below zero
    }

    @Test
    fun resettingTheScoreSetsItAtZero() {
        val fragmentArgs = bundleOf("player" to "Jean")
        val scenario = launchFragmentInContainer<PlayerButton>(fragmentArgs)

        onView(withId(R.id.player_button)).perform(click())
        onView(withId(R.id.player_button)).perform(click())
        onView(withId(R.id.current_score)).check(matches(withText("002")))

        scenario.onFragment { fragment ->
            fragment.resetScore()
        }
        onView(withId(R.id.current_score)).check(matches(withText("000")))
    }
}