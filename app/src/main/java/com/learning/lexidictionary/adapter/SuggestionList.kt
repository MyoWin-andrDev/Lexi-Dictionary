package com.learning.lexidictionary.adapter

import android.content.Context
import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.R
import com.learning.lexidictionary.databinding.ActivityMainBinding
import com.learning.lexidictionary.databinding.SuggestionLayoutBinding
import com.learning.lexidictionary.model.search.Result
import com.learning.lexidictionary.model.search.WordSearch
import retrofit2.http.Query

class SuggestionList(private val context: Context, val wordList : List<Result>, val inputWord : String) : RecyclerView.Adapter<SuggestionList.SuggestionViewHolder>(){

    class SuggestionViewHolder(val binding : SuggestionLayoutBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionViewHolder {
      //  TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SuggestionLayoutBinding.inflate(layoutInflater)
        return SuggestionViewHolder(binding)
    }

    override fun getItemCount(): Int {
       // TODO("Not yet implemented")
        return wordList.size
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
       // TODO("Not yet implemented")
        val definition = wordList[position]
        val label = definition.label
        holder.binding.word.text = highlightSearchQuery( label, inputWord )
    }

    private fun highlightSearchQuery(label : String, searchQuery : String ) : SpannableString {
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
}