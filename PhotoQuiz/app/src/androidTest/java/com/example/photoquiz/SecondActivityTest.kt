package com.example.photoquiz

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.photoquiz.view.SecondActivity
import org.hamcrest.CoreMatchers.not

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class SecondActivityTest {

    @get:Rule
    var secondActivityRule: ActivityScenarioRule<SecondActivity> =
        ActivityScenarioRule(SecondActivity::class.java)

    @Test
    fun shouldHaveAllComponents() {
        onView(withId(R.id.image_question_1)).check(matches(isDisplayed()))
        onView(withId(R.id.choice1)).check(matches(isDisplayed()))
        onView(withId(R.id.choice2)).check(matches(isDisplayed()))
        onView(withId(R.id.choice3)).check(matches(isDisplayed()))
        onView(withId(R.id.choice4)).check(matches(isDisplayed()))
        onView(withId(R.id.timer)).check(matches(isDisplayed()))
        onView(withId(R.id.popupImage)).check(matches(not(isDisplayed())))
    }

    @Test
    fun shouldDisableButtonsOnSingleButtonClick() {
        onView(withId(R.id.choice1)).perform(click())
        onView(withId(R.id.choice1)).check(matches(not(isSelected())))
        onView(withId(R.id.choice2)).check(matches(not(isSelected())))
        onView(withId(R.id.choice3)).check(matches(not(isSelected())))
        onView(withId(R.id.choice4)).check(matches(not(isSelected())))
    }

    @Test
    fun shouldDisplayPopupImageOnButtonClick() {
        onView(withId(R.id.choice1)).perform(click())
        onView(withId(R.id.popupImage)).check(matches(isEnabled()))
    }
}
