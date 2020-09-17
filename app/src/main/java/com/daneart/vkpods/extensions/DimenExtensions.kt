package com.daneart.vkpods.extensions

import android.content.res.Resources
import android.util.TypedValue

fun Float.dpToPix(resources: Resources): Float = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this,
    resources.displayMetrics
)