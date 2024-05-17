package com.edrhian.kotlinapp3tex

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(SignUp::class.java)

    @Test
    fun testRegistroUsuarioExitoso() {

        Espresso.onView(ViewMatchers.withId(R.id.editTextName))
            .perform(ViewActions.typeText("King88"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextEmailAddress))
            .perform(ViewActions.typeText("king@gmail.com"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editTextPassword))
            .perform(ViewActions.typeText("Ki123456"), ViewActions.closeSoftKeyboard())
        Espresso.onView(ViewMatchers.withId(R.id.editTextTextPassword))
            .perform(ViewActions.typeText("Ki123456"), ViewActions.closeSoftKeyboard())



        Espresso.onView(ViewMatchers.withId(R.id.btn_online)).perform(ViewActions.click())


        Espresso.onView(ViewMatchers.withId(R.id.menu))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
