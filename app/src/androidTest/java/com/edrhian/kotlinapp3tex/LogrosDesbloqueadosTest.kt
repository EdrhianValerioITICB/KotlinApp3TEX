package com.edrhian.kotlinapp3tex

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LogrosDesbloqueadosTest {

    @get:Rule
    var activityRule: ActivityScenarioRule<LogrosDesbloqueados> =
        ActivityScenarioRule(LogrosDesbloqueados::class.java)

    @Test
    fun verifyRecyclerViewItemCount() {
        Thread.sleep(2000)

        onView(withId(R.id.rv_list_achievements)).check(RecyclerViewItemCountAssertion(0))
    }

    class RecyclerViewItemCountAssertion(private val expectedItemCount: Int) : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (view !is RecyclerView) {
                throw noViewFoundException!!
            }

            val adapter = view.adapter
            assertThat(adapter?.itemCount, `is`(expectedItemCount))
        }
    }
}
