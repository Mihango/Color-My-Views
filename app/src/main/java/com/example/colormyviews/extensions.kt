package com.example.colormyviews

import android.content.res.ColorStateList
import java.lang.reflect.Field


fun android.graphics.drawable.RippleDrawable.getRippleState(): ColorStateList {
    val field: Field = javaClass.getDeclaredField("mState")
    field.isAccessible = true
    val value: ColorStateList = field.get(ColorStateList(null, null)) as ColorStateList
    return value
}