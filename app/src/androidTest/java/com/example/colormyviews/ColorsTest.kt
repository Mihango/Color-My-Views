package com.example.colormyviews

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
import androidx.annotation.ColorInt
import androidx.annotation.NonNull
import androidx.test.internal.util.Checks
import org.hamcrest.Matcher
import org.w3c.dom.Text


@RunWith(AndroidJUnit4::class)
class ColorsTest {

    @get:Rule val activityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testButtonRedSetsBoxThreeToRed() {
        onView(withId(R.id.button_red)).perform(click())
        onView(withId(R.id.box_three_text)).check(matches(withBackgroundColor(R.color.my_red)))

//        object: BoundedMatcher<View, TextView>(TextView::class.java) {
//            override fun describeTo(description: Description?) {}
//
//            override fun matchesSafely(item: TextView?): Boolean {
//                val color: Drawable = item?.background!!
//                val colorToMatch: Drawable = ContextCompat.getDrawable(item.context!!, R.color.my_red)!!
//                return color == colorToMatch
//            }
//        }))
    }

    private fun withBackgroundColor(@ColorInt color: Int): Matcher<View> {
        Checks.checkNotNull(color)
        return object : BoundedMatcher<View, TextView>(TextView::class.java) {
            public override fun matchesSafely(warning: TextView): Boolean {
                val color2 = (warning.background as ColorDrawable).color
                println("Color 1 $color == $color2")
                return color == color2
            }

            override fun describeTo(description: Description) {
                description.appendText("with text color: ")
            }
        }
    }
}