package com.example.video_solution


import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import org.hamcrest.CoreMatchers
import org.junit.Test

class PlaylistDetailsFeature :BaseUiTest() {

    @Test
    fun displaysPlayListsNameAndDetails(){
        navigateToPlayListDetails()
        BaristaVisibilityAssertions.assertDisplayed("Hard Rock Cafe")
        BaristaVisibilityAssertions.assertDisplayed("Rock your " +
                "senses with this timeless signature vibe list. \n\n • Poison \n • You shook me all" +
                " night \n • Zombie \n • Rock'n Me \n • Thunderstruck \n • " +
                "I Hate Myself for Loving you \n • Crazy \n • Knockin' on Heavens Door")

    }


    @Test
    fun displayLoaderWhiteFetchingThePlaylistDetails(){
        navigateToPlayListDetails()

        unregisterIdlingResource()

        BaristaVisibilityAssertions.assertDisplayed(R.id.details_progress_bar)
    }

    @Test
    fun displayErrorMessageWhenNetworkFails(){
        navigateToPlayListDetails()

        unregisterIdlingResource()

        BaristaVisibilityAssertions.assertDisplayed(R.string.generic_error)
    }

    @Test
    private fun navigateToPlayListDetails() {
        Espresso.onView(
            CoreMatchers.allOf(
                ViewMatchers.withId(R.id.list_category),
                ViewMatchers.isDescendantOfA(nthChildOf(ViewMatchers.withId(R.id.list), 0))
            )
        )
            .perform(ViewActions.click())
    }

}