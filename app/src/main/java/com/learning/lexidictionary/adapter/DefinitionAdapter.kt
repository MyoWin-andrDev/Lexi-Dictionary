package com.learning.lexidictionary.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.DefinitonLayoutBinding
import com.learning.lexidictionary.model.learnerEdition.Eg
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem

class DefinitionAdapter(val context : Context, val learnerDataItemList : List<LearnerDataItem>, val wordId : String) : RecyclerView.Adapter<DefinitionAdapter.PhrasesViewHolder>(){
    val definitionList = Definition().getDefinition(learnerDataItemList)

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
        return 1
    }

    override fun onBindViewHolder(holder: PhrasesViewHolder, position: Int) {
      //  TODO("Not yet implemented")
        val binding = holder.binding
        val definitionData = learnerDataItemList[position]
        //Retrieving Data at Index 0
        val defList = Definition().getDefinitionAtIndex(learnerDataItemList, 0)
        //Definition
        val definition = defList[0] as List<*>
        if(definition != null){
            binding.senDefinition.text = Definition().replaceBCString(definition[1].toString())
        }
        val example = defList[1] as List<*>
        if(example != null){
            if(example[0]!! == "vis"){
                val egList = example[1] as List<*>
                val t = egList[0] as List<*>
                Log.d("Eg", t.toString())

                //binding.senDefEg1.text = t.toString()
//                for(i in t.toString()){
//                   binding.senDefEg1.text = egList[i].toString()
//                }
            }
        }





        //Retrieving Definition at Index 1
        val defList1 = Definition().getDefinitionAtIndex(learnerDataItemList, 1)
        val definition1 = defList1[0] as List<*>
        if(definition1 != null){
            binding.senShortDefinition.text = Definition().replaceBCString(definition1[1].toString())
        }
        //Retrieving Definition
//        when(definition){
//            1 -> binding.senDefinition.text = definition[0][0][0].toString()
//        }
        //Retrieving Examples
//        val phrasesList = index.phrases
//        Log.d("phrasesList", phrasesList.toString())
//        val phrasesSize = phrasesList.size
//        Log.d("size", phrasesSize.toString())
//
//        when(phrasesSize){
//            1 -> {
//                holder.binding.senDefinition.isVisible = true
//                holder.binding.phrases1.text = phrasesList[0].text
//            }
//            2 -> {
//                holder.binding.phrases1.isVisible = true
//                holder.binding.phrases1.text = phrasesList[0].text
//                holder.binding.phrases2.isVisible = true
//                holder.binding.phrases2.text = phrasesList[1].text
//            }
//            3 -> {
//                holder.binding.phrases1.isVisible = true
//                holder.binding.phrases1.text = phrasesList[0].text
//                holder.binding.phrases2.isVisible = true
//                holder.binding.phrases2.text = phrasesList[1].text
//                holder.binding.phrases3.isVisible = true
//                holder.binding.phrases3.text = phrasesList[2].text
//            }
//            in 4 .. 10-> {
//                holder.binding.phrases1.isVisible = true
//                holder.binding.phrases1.text = phrasesList[0].text
//                holder.binding.phrases2.isVisible = true
//                holder.binding.phrases2.text = phrasesList[1].text
//                holder.binding.phrases3.isVisible = true
//                holder.binding.phrases3.text = phrasesList[2].text
//                holder.binding.phrases4.isVisible = true
//                holder.binding.phrases4.text = phrasesList[3].text
//            }
//
//        }
    }


}