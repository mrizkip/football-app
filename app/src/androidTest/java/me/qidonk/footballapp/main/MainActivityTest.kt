package me.qidonk.footballapp.main

import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import me.qidonk.footballapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.nextMatch_recyclerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.nextMatch_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.nextMatch_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testAppBehaviour() {
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.nextMatch_recyclerView))
                .check(ViewAssertions.matches(isDisplayed()))
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.nextMatch_recyclerView))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.nextMatch_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        delay(2)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .perform(click())
        Espresso.onView(ViewMatchers.withText("Added to favorite"))
                .check(ViewAssertions.matches(isDisplayed()))
        delay()
        pressBack()
        Espresso.onView(ViewMatchers.withId(R.id.main_bottomNavigation))
                .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.menu_favorites))
                .perform(click())
        delay()
        Espresso.onView(ViewMatchers.withId(R.id.favoritematch_recyclerView))
                .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.favoritematch_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        delay(2)
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.add_to_favorite))
                .perform(click())
        Espresso.onView(ViewMatchers.withText("Removed from favorite"))
                .check(ViewAssertions.matches(isDisplayed()))
        pressBack()
    }

    private fun delay(second: Long = 1) {
        try {
            Thread.sleep(1000 * second)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}