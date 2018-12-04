package me.qidonk.footballapp.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
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
        onView(withId(R.id.lastMatch_recyclerView))
            .check(matches(ViewMatchers.isDisplayed()))
        delay()
        onView(withId(R.id.lastMatch_recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        delay()
        onView(withId(R.id.lastMatch_recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
    }

    @Test
    fun testAppBehaviour() {
        delay()
        onView(withId(R.id.lastMatch_recyclerView))
            .check(matches(isDisplayed()))
        delay()
        onView(withId(R.id.lastMatch_recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        delay()
        onView(withId(R.id.lastMatch_recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        delay()
        onView(withId(R.id.add_to_favorite))
            .check(matches(isDisplayed()))
        delay()
        onView(withId(R.id.add_to_favorite))
            .perform(click())
        delay()
        onView(withText("Added to favorite"))
            .check(matches(isDisplayed()))
        delay()
        onView(withId(R.id.add_to_favorite))
            .perform(click())
        delay()
        onView(withText("Removed from favorite"))
            .check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.main_bottomNavigation))
            .check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorites))
            .perform(click())
    }

    private fun delay() {
        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}