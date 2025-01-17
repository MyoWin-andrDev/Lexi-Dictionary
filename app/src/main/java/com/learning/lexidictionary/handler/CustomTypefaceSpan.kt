package com.learning.lexidictionary.handler

import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.TypefaceSpan

class CustomTypefaceSpan(family : String, typeface : Typeface) : TypefaceSpan(family) {
    private val newTypeface = typeface
    override fun updateDrawState(ds: TextPaint) {
        super.updateDrawState(ds)
        applyCustomTypeface(ds)
    }

    override fun updateMeasureState(paint: TextPaint) {
        super.updateMeasureState(paint)
        applyCustomTypeface(paint)
    }

    private fun applyCustomTypeface(paint : Paint){
        paint.typeface = newTypeface
    }
}