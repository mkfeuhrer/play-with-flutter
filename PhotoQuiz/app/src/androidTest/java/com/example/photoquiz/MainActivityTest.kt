package com.example.photoquiz

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.photoquiz.view.MainActivity
import com.example.photoquiz.view.SecondActivity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mainActivityRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)
    @get:Rule
    var secondActivityRule: IntentsTestRule<SecondActivity> =
        IntentsTestRule(SecondActivity::class.java)

    @Test
    fun shouldOpenSecondScreenWhenButtonIsClicked() {
        mainActivityRule.launchActivity(Intent())
        onView(withId(R.id.button)).perform(click())
        intended(hasComponent(SecondActivity::class.java.name))
    }

    @Test
    fun shouldHaveAllComponents() {
        mainActivityRule.launchActivity(Intent())
        onView(withId(R.id.gojekImage)).check(matches(isDisplayed()))
        onView(withId(R.id.button)).check(matches(isDisplayed()))
        onView(withId(R.id.highScores)).check(matches(isDisplayed()))
    }
}
