package com.learning.lexidictionary.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.databinding.DefinitonLayoutBinding
import com.learning.lexidictionary.model.entry.Result

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
        val getSenses = index.entries[position].senses[position]
       // holder.binding.senDefinition.text = getSenses.definitions[0]
        holder.binding.senShortDefinition.text = getSenses.shortDefinitions[0]
        holder.binding.subSenDefinition.text = getSenses.subsenses[position].definitions[0]
        holder.binding.subSenShortDefinition.text = getSenses.subsenses[position].shortDefinitions[0]

        //Retrieving Phrases
        val phrasesList = index.phrases
        Log.d("phrasesList", phrasesList.toString())
        val phrasesSize = phrasesList.size
        Log.d("size", phrasesSize.toString())

        when(phrasesSize){
            1 -> {
                holder.binding.phrases1.isVisible = true
                holder.binding.phrases1.text = phrasesList[0].text
            }
            2 -> {
                holder.binding.phrases1.isVisible = true
                holder.binding.phrases1.text = phrasesList[0].text
                holder.binding.phrases2.isVisible = true
                holder.binding.phrases2.text = phrasesList[1].text
            }
            3 -> {
                holder.binding.phrases1.isVisible = true
                holder.binding.phrases1.text = phrasesList[0].text
                holder.binding.phrases2.isVisible = true
                holder.binding.phrases2.text = phrasesList[1].text
                holder.binding.phrases3.isVisible = true
                holder.binding.phrases3.text = phrasesList[2].text
            }
            in 4 .. 10-> {
                holder.binding.phrases1.isVisible = true
                holder.binding.phrases1.text = phrasesList[0].text
                holder.binding.phrases2.isVisible = true
                holder.binding.phrases2.text = phrasesList[1].text
                holder.binding.phrases3.isVisible = true
                holder.binding.phrases3.text = phrasesList[2].text
                holder.binding.phrases4.isVisible = true
                holder.binding.phrases4.text = phrasesList[3].text
            }

        }
    }


}