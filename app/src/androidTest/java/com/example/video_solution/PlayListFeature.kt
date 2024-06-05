package com.example.video_solution

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.adevinta.android.barista.assertion.BaristaRecyclerViewAssertions
import com.adevinta.android.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.adevinta.android.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlayListFeature {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


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
            .check(matches(withText("Rock")))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.playlist_image), isDescendantOfA(nthChildOf(withId(R.id.list),0))))
            .check(matches(withDrawable(R.drawable.playlist)))
            .check(matches(isDisplayed()))

    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }
        }
    }
}