package saauan.ramenpartycompanion

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
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
    }
}