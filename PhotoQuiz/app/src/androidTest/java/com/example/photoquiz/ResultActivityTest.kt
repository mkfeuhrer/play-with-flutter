package com.example.photoquiz

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.photoquiz.view.QuizResultActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultActivityTest {

    @get:Rule
    var resultActivityRule: ActivityScenarioRule<QuizResultActivity> =
        ActivityScenarioRule(QuizResultActivity::class.java)

    @Test
    fun shouldHaveAllComponents() {
        onView(withId(R.id.wrongResponses)).check(matches(isDisplayed()))
        onView(withId(R.id.correctResponses)).check(matches(isDisplayed()))
        onView(withId(R.id.skippedQuestions)).check(matches(isDisplayed()))
        onView(withId(R.id.totalTimeTakenValue)).check(matches(isDisplayed()))
    }
}
