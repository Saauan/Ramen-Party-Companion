package saauan.ramenpartycompanion

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstFragmentInstrumentedTest {
    @Test
    fun onCreation_hasFiveFragments() {
        val scenario = launchFragmentInContainer<FirstFragment>()
        scenario.onFragment { fragment ->
            assertThat(fragment.childFragmentManager.fragments.size, equalTo(5))
        }
    }

    @Test
    fun when_pressing_reset_resets_the_scores() {
        launchFragmentInContainer<FirstFragment>()
        incrementScore()
        incrementScore()
        checkScore("002")

        onView(withId(R.id.button_reset)).perform(click())
        //checkScore("002")
        onView(withText("Confirmer"))
            .inRoot(isDialog())
            .check(matches(isDisplayed()))
            .perform(click())

        checkScore("000")
    }

    private fun checkScore(expectedScore: String) {
        onView(matchPaulScore()).check(matches(withText(expectedScore)))
    }

    private fun incrementScore() {
        onView(matchPaulButton()).perform(click())
    }

    private fun matchPaulButton() = allOf(withId(R.id.player_button), withText("Paul"))
    private fun matchPaulScore() = allOf(withId(R.id.current_score), hasSibling(matchPaulButton()))
}