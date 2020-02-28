package com.example.photoquiz

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.photoquiz.view.HighScoreActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HighScoreActivityTest {

    @get:Rule
    var resultActivityRule: ActivityScenarioRule<HighScoreActivity> =
        ActivityScenarioRule(HighScoreActivity::class.java)

    @Test
    fun shouldHaveAllComponents() {
        onView(withId(R.id.highScoreRecyclerView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.textView5))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.textView7))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
