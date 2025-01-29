package com.learning.lexidictionary.handler

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.SpannableString
import android.text.TextPaint
import android.text.style.ForegroundColorSpan
import android.text.style.TypefaceSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.text.toSpannable
import com.learning.lexidictionary.R
@RequiresApi(Build.VERSION_CODES.O)

class StringHandler(val searchQuery: String, val context: Context) {
    var spannableString = SpannableString(searchQuery)
     fun  highlightSearchQuery(label : String, searchQuery : String, context : Context) : SpannableString {
        val spannableString = SpannableString(label)
        val startIndex = label.indexOf(searchQuery, ignoreCase = true)
        if(startIndex >= 0){
            val endIndex = startIndex + searchQuery.length
            spannableString.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.blue)),
                startIndex,
                endIndex,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return spannableString
    }

    fun typefaceSearchQuery(label : String, searchQuery : String, context : Context) : SpannableString{
        //Versatile Usage Regular Expression
        val newLabel = label.replace(Regex("\\{it\\}(.*?)\\{/it\\}"), "$1")
        val spannableString = SpannableString(newLabel)
        val startIndex = newLabel.indexOf(searchQuery, ignoreCase = true)
        val customFont = context.resources.getFont(R.font.opensans_italic_bold)
        if(startIndex >= 0){
            val endIndex = startIndex + searchQuery.length
            spannableString.setSpan(
                CustomTypefaceSpan("",customFont),
                startIndex,
                endIndex,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            spannableString.setSpan(
                UnderlineSpan(),
                startIndex,
                endIndex,
                SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        return spannableString
    }

    fun hyperLinkGuidance(label : String, searchQuery : String, context : Context) : SpannableString{
        val newLabel = label
            .replaceFirst("{dx}","")
            .replaceFirst("{/dx}","")
            .replaceFirst("{dxt|","")
            .replaceFirst("||}","")
            .uppercase()
        spannableString = SpannableString(newLabel)
        val seeIndex = newLabel.indexOf("See")
        val seeLength = seeIndex + "see".length + 2 // 2 for space index
        val endIndex = newLabel.length
        spannableString.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context,R.color.blue)),
            seeLength,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            UnderlineSpan(),
            seeLength,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
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

    fun checkGuidanceWord(word : String, searchQuery: String, context: Context) : SpannableString{
        if(word.startsWith("{dx}see")){
            return hyperLinkGuidance(word,searchQuery,context)
        }
        return typefaceSearchQuery(word,searchQuery,context)
    }

    fun highlightPhraseInExample(example : String, phrase : String )  : SpannableString{
        val replacedExample = example.replaceFirst("{it}", "").replaceFirst("{/it}", "")
        spannableString = SpannableString(replacedExample)
        val startIndex = replacedExample.indexOf(phrase, ignoreCase = true)
        val endIndex = startIndex + phrase.length
        spannableString.setSpan(
            UnderlineSpan(),
            startIndex,
            endIndex,
            SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return spannableString
    }

}

