package com.example.aroundgym.presenter

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.aroundgym.R
import kotlinx.coroutines.*
import org.hamcrest.Matchers.not
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Thread.sleep

@RunWith(AndroidJUnit4::class)
class IntroActivityTest {

    @Test
    fun should_not_show_icon_when_after_delay() {

        ActivityScenario.launch(IntroActivity::class.java)

        onView(withId(R.id.btn_search)).check(matches(isDisplayed()))
    }
}