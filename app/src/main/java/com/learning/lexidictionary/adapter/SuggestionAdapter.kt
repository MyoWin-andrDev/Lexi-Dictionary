package com.learning.lexidictionary.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.R
import com.learning.lexidictionary.databinding.SuggestionLayoutBinding
import com.learning.lexidictionary.handler.StringHandler
import com.learning.lexidictionary.view.DefinitionActivity
@RequiresApi(Build.VERSION_CODES.O)
class SuggestionAdapter(private val context: Context, val stemList : List<String>, val inputWord : String) : RecyclerView.Adapter<SuggestionAdapter.SuggestionViewHolder>(){

    private val stringHandler = StringHandler(inputWord, context)
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
        return stemList.size
    }

    override fun onBindViewHolder(holder: SuggestionViewHolder, position: Int) {
       // TODO("Not yet implemented")
        val stemWord = stemList[position]
        val label = stemWord
        holder.binding.word.text = stringHandler.highlightSearchQuery( label, inputWord , context)
        holder.binding.word.setOnClickListener(){
            val intent = Intent(context, DefinitionActivity::class.java)
            val query = holder.binding.word.text
            intent.putExtra("id", label)
            context.startActivity(intent)
        }
    }

}