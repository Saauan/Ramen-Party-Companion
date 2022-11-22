package saauan.ramenpartycompanion

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultInstrumentedTest {

    @Test
    fun canLaunchWithNoPlayers() {
        launchFragmentInContainer<ResultFragment>()
    }

    @Test
    fun canLaunchWithNoPlayersAlt() {
        val fragmentArgs = bundleOf()
        launchFragmentInContainer<ResultFragment>(fragmentArgs)
    }

    @Test
    fun displaysAllThePlayers() {
        val fragmentArgs = bundleOf(
            ARG_PLAYERS to arrayListOf(
                Player("Jean", 3),
                Player("Paul", 4),
                Player("Tristan", 0),
            )
        )
        launchFragmentInContainer<ResultFragment>(fragmentArgs)

        onView(allOf(withParent(withId(R.id.score_layout)), withText("Jean : 3")))
            .check(matches(isDisplayed()))
        onView(allOf(withParent(withId(R.id.score_layout)), withText("Paul : 4")))
            .check(matches(isDisplayed()))
        onView(allOf(withParent(withId(R.id.score_layout)), withText("Tristan : 0")))
            .check(matches(isDisplayed()))

    }
}