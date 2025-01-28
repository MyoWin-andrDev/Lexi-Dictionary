package com.learning.lexidictionary.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.DefinitionLayoutBinding
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.handler.DefinitionHandler

@RequiresApi(Build.VERSION_CODES.O)
class DefinitionAdapter(val context : Context, val learnerDataItemList : List<LearnerDataItem>, val wordId : String) : RecyclerView.Adapter<DefinitionAdapter.DefinitionViewHolder>(){
    val defClass = Definition()
    val definitionList = defClass.getDefinition(learnerDataItemList)

    class DefinitionViewHolder (val binding : DefinitionLayoutBinding ) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefinitionViewHolder {
       // TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DefinitionLayoutBinding.inflate(layoutInflater)
        return DefinitionViewHolder(binding)
    }

    override fun getItemCount(): Int {
      //  TODO("Not yet implemented")
        return 1
    }


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DefinitionViewHolder, position: Int) {
        //  TODO("Not yet implemented")
        val binding = holder.binding
        val definitionData = learnerDataItemList[position]
        val sSEQIndices = learnerDataItemList[0].def[0].sseq.indices
        for(i in sSEQIndices) {
            DefinitionHandler(context, wordId).handleDefAndEg(learnerDataItemList, i, holder)
        }
    }
}