package com.learning.lexidictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.databinding.SuggestionLayoutBinding
import com.learning.lexidictionary.model.search.Result
import com.learning.lexidictionary.model.search.WordSearch

class SuggestionList(private val context: Context, val wordList : List<Result>) : RecyclerView.Adapter<SuggestionList.SuggestionViewHolder>(){

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
        holder.binding.word.text = definition.label
    }
}