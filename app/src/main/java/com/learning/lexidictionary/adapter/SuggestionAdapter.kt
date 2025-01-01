package com.learning.lexidictionary.adapter

import android.content.Context
import android.content.Intent
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.MainActivity
import com.learning.lexidictionary.R
import com.learning.lexidictionary.databinding.SuggestionLayoutBinding
import com.learning.lexidictionary.model.search.Result
import com.learning.lexidictionary.view.DefinationActivity

class SuggestionAdapter(private val context: Context, val wordList : List<Result>, val inputWord : String) : RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>(){

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
        holder.binding.word.setOnClickListener(){
            val getId = wordList[0].id
            val intent = Intent(context, DefinationActivity::class.java)
            val query = holder.binding.word.text
            intent.putExtra("id", query)
            context.startActivity(intent)
            Log.d("getId",getId)
        }
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