package com.example.video_solution

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayListFeature :BaseUiTest() {

    @Test
    fun displayScreenTitle() {
        assertDisplayed("PlayLists")
    }

    @Test
    fun displaysPlaylists(){

        BaristaRecyclerViewAssertions.assertRecyclerViewItemCount(R.id.list,10)

        onView(allOf(withId(R.id.palylist_name), isDescendantOfA(nthChildOf(withId(R.id.list),0))))
            .check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.list_category), isDescendantOfA(nthChildOf(withId(R.id.list),0))))
            .check(matches(withText("rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.list),0))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun displaysRockImageForListItem(){

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.list),0))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.list),3))))
            .check(matches(withDrawable(R.drawable.rock)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun hidesLoader(){
        BaristaVisibilityAssertions.assertNotDisplayed(R.id.loadDisplay)
    }

    @Test
    fun displaysLoaderWhileFetchingPlayList(){//is so fast that it is not visible the loader i added some delay in mockoon
        unregisterIdlingResource()
        assertDisplayed(R.id.loadDisplay)
    }

    @Test
    fun navigateToDetailScreen(){
        onView(allOf(withId(R.id.list_category), isDescendantOfA(nthChildOf(withId(R.id.list),0))))
            .perform(click())

        assertDisplayed(R.id.playlist_detail_root)
    }

    @Test
    fun displayErrorMessageWhenNetworkFails() {
        unregisterIdlingResource()
        BaristaVisibilityAssertions.assertDisplayed(R.string.generic_error)
    }

}


