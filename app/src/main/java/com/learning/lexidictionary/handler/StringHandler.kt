package com.learning.lexidictionary.handler

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.learning.lexidictionary.R
@RequiresApi(Build.VERSION_CODES.O)

class StringHandler(val searchQuery: String, val context: Context) {
    var spannableString = SpannableString(searchQuery)
    val typeface = context.resources.getFont(R.font.opensans_italic)

     fun  highlightSearchQuery(label : String, searchQuery : String, context : Context) : SpannableString {
        val spannableString = SpannableString(label)
        val startIndex = label.indexOf(searchQuery, ignoreCase = true)
        if(startIndex >= 0){
            val endIndex = startIndex + searchQuery.length
//            if(label == "{it}$searchQuery{/it}"){
//                spannableString.setSpan(
//                    ForegroundColorSpan(ContextCompat.getColor(context, R.color.blue)),
//                    startIndex,
//                    endIndex,
//                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
//            }
//            else{
//                spannableString.setSpan(
//                    ForegroundColorSpan(ContextCompat.getColor(context, R.color.blue)),
//                    startIndex,
//                    endIndex,
//                    SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE)
//            }
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.blue)),
                startIndex,
                endIndex,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return spannableString
    }

    fun replaceBCString( string : String) : SpannableString {
        val occurrence = string.split("{bc}").size - 1
        val formattedString = when (occurrence) {
            0 -> string
            1 -> string.replace("{bc}", "\u2022 ")
            else -> string.replaceFirst("{bc}","\u2022").replaceFirst("{bc}","\n\u003a")
        }
       return highlightSearchQuery(formattedString, searchQuery, context)
    }

}