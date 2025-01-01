package com.learning.lexidictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.databinding.DefinitonLayoutBinding
import com.learning.lexidictionary.model.entries.Result

class DefinitionAdapter(val context : Context, val resultList : List<Result>, val wordId : String) : RecyclerView.Adapter<DefinitionAdapter.PhrasesViewHolder>(){
    val lexicalEntry = resultList[0].lexicalEntries
    class PhrasesViewHolder ( val binding : DefinitonLayoutBinding ) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhrasesViewHolder {
       // TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DefinitonLayoutBinding.inflate(layoutInflater)
        return PhrasesViewHolder(binding)
    }

    override fun getItemCount(): Int {
      //  TODO("Not yet implemented")
        return lexicalEntry.size
    }

    override fun onBindViewHolder(holder: PhrasesViewHolder, position: Int) {
      //  TODO("Not yet implemented")
        val index = lexicalEntry[position]
        holder.binding.senDefinition.text = index.entries[position].senses[position].definitions[position]
        holder.binding.senShortDefinition.text = index.entries[position].senses[position].shortDefinitions[position]
        val phrasesList = index.phrases.size
//         phrasesList.forEach { index ->
//            holder.binding.phrases.text = index.toString()
//        }
        for(i in 0 until phrasesList){
            holder.binding.phrases.text = index.phrases[i].text
        }
    }
}