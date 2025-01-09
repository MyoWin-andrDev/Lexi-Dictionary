package com.learning.lexidictionary.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.DefinitonLayoutBinding
import com.learning.lexidictionary.model.learnerEdition.Eg
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem

class DefinitionAdapter(val context : Context, val learnerDataItemList : List<LearnerDataItem>, val wordId : String) : RecyclerView.Adapter<DefinitionAdapter.PhrasesViewHolder>(){
    val defClass = Definition()
    val definitionList = defClass.getDefinition(learnerDataItemList)

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


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PhrasesViewHolder, position: Int) {
      //  TODO("Not yet implemented")
        val binding = holder.binding
        val definitionData = learnerDataItemList[position]

        //Retrieving Data at Index 0
        val defList = defClass.getDefinitionAtIndex(learnerDataItemList, 0)
        //Definition
        val definition = defList[0] as List<*>
        if(definition != null && definition.isNotEmpty()){
            binding.senDefinition.text = defClass.replaceBCString(definition[1].toString())
        }
        //Example
        val example = defList[1] as List<*>
        if(example != null){
            if(example[0]!! == "vis"){
                val vis = example[1] as List<*>
                val exampleList = defClass.linkedTreeMapToList(vis)
                Log.d("vis", vis.size.toString())
                Log.d("egList", exampleList.size.toString())
                when(exampleList.size){
                    1 -> binding.senDefEg1.text = exampleList[0].t
                    2 -> binding.senDefEg1.text = exampleList[0].t +"\n" + exampleList[1].t
                    3 -> binding.senDefEg1.text = exampleList[0].t +"\n" + exampleList[1].t +"\n" + exampleList[2].t
                    in 4..10 -> binding.senDefEg1.text = exampleList[0].t +"\n" + exampleList[1].t +"\n" + exampleList[2].t +"\n" + exampleList[3].t
                    else -> binding.senDefEg1.isVisible = false
                }
            }
        }

        //Retrieving Definition at Index 1
        //Definition1
        val defList1 = Definition().getDefinitionAtIndex(learnerDataItemList, 1)
        val definition1 = defList1[0] as List<*>
        if(definition1 != null){
            binding.senShortDefinition.text = Definition().replaceBCString(definition1[1].toString())
        }
        //Example1
        val example1 = defList1[1] as List<*>
        if(example1 != null && example1.isNotEmpty()){
            if(example1[0]!! == "vis"){
                val vis1 = example1[1] as List<*>
                val example1List = defClass.linkedTreeMapToList(vis1)
                when(example1List.size){
                    1 -> binding.senShortDefEg1.text = example1List[0].t
                    2 -> binding.senShortDefEg1.text = example1List[0].t +"\n" + example1List[1].t
                    3 -> binding.senShortDefEg1.text = example1List[0].t +"\n" + example1List[1].t +"\n" + example1List[2].t
                    in 4..10 -> binding.senShortDefEg1.text = example1List[0].t +"\n" + example1List[1].t +"\n" + example1List[2].t +"\n" + example1List[3].t
                    else -> binding.senShortDefEg1.isVisible = false
                }
            }
        }
        //Retrieving Definition At Index 2
        val defList2 = defClass.getDefinitionAtIndex(learnerDataItemList, 2)
        val definition2 = defList2[0] as List<*>
        //Definition 2
        if(definition2 != null && definition2.isNotEmpty()){
            binding.subSenDefinition.text = defClass.replaceBCString(definition2[1].toString())
        }
        // Example 2
        val example2 = defList2[1] as List<*>
        if(example2[0]!! == "vis"){
            val vis2 = example2[1] as List<*>
            val example2List = defClass.linkedTreeMapToList(vis2)
            when(example2List.size){
                1 -> binding.subSenDefEg1.text = example2List[0].t
                2 -> binding.subSenDefEg1.text = example2List[0].t +"\n" + example2List[1].t
                3 -> binding.subSenDefEg1.text = example2List[0].t +"\n" + example2List[1].t +"\n" + example2List[2].t
                in 4..10 -> binding.subSenDefEg1.text = example2List[0].t +"\n" + example2List[1].t +"\n" + example2List[2].t +"\n" + example2List[3].t
                else -> binding.subSenDefEg1.isVisible = false
            }
        }

        //Retrieving Definition At Index 3
        val defList3 = defClass.getDefinitionAtIndex(learnerDataItemList, 4)
        val definition3 = defList3[0] as List<*>
        //Definition 2
        if(definition3 != null && definition3.isNotEmpty()){
            binding.subSenShortDefinition.text = defClass.replaceBCString(definition3[1].toString())
        }
        // Example 3
        val example3 = defList3[1] as List<*>
        if(example3[0]!! == "vis"){
            val vis3 = example3[1] as List<*>
            val example3List = defClass.linkedTreeMapToList(vis3)
            when(example3List.size){
                1 -> binding.subSenShortDefEg1.text = example3List[0].t
                2 -> binding.subSenShortDefEg1.text = example3List[0].t +"\n" + example3List[1].t
                3 -> binding.subSenShortDefEg1.text = example3List[0].t +"\n" + example3List[1].t +"\n" + example3List[2].t
                in 4..10 -> binding.subSenShortDefEg1.text = example3List[0].t +"\n" + example3List[1].t +"\n" + example3List[2].t +"\n" + example3List[3].t
                else -> binding.subSenShortDefEg1.isVisible = false
            }
        }
    }
}