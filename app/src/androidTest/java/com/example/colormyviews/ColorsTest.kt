package com.example.colormyviews

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.hamcrest.Description
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.RippleDrawable
import android.util.Log
import androidx.annotation.ColorInt
import androidx.test.internal.util.Checks
import org.hamcrest.Matcher


@RunWith(AndroidJUnit4::class)
class ColorsTest {

    @get:Rule val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testButtonRed_setsBoxThreeBackground_toRed() {
        onView(withId(R.id.button_red)).perform(click())
        onView(withId(R.id.box_three_text)).check(matches(withBackgroundColor(R.color.my_red)))
    }

    @Test
    fun testButtonYellow_setsBoxFourBackground_toYellow() {
        onView(withId(R.id.button_yellow)).perform(click())
        onView(withId(R.id.box_four_text)).check(matches(withBackgroundColor(R.color.my_yellow)))
    }

    @Test
    fun testButtonGreen_setsBoxFiveBackground_toGreen() {
        onView(withId(R.id.button_green)).perform(click())
        onView(withId(R.id.box_five_text)).check(matches(withBackgroundColor(R.color.my_green)))
    }

    private fun withBackgroundColor(@ColorInt color: Int): Matcher<View> {
        Checks.checkNotNull(color)
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            override fun describeTo(description: Description?) {
                description?.appendText("TextView background color to be $color")
            }

            override fun matchesSafely(item: TextView?): Boolean {
                val backgroundColor = item?.background as ColorDrawable
                val colorDrawable = ColorDrawable(ContextCompat.getColor(item.context, color))
                return colorDrawable.color == backgroundColor.color
            }

        }
    }
}