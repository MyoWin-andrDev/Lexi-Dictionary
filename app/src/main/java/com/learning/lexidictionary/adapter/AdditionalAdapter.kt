package com.learning.lexidictionary.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.databinding.AdditionalLayoutBinding
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.handler.DefinitionHandler
import com.learning.lexidictionary.view.DefinitionActivity

@RequiresApi(Build.VERSION_CODES.O)
class AdditionalAdapter(val context : Context, val learnerItemList : List<LearnerDataItem>, val wordId : String) : RecyclerView.Adapter<AdditionalAdapter.AdditionalViewHolder>() {
    private val defClass = Definition()
    private val defHandlerClass = DefinitionHandler(context, wordId)
    private lateinit var defBinding : ActivityDefinationBinding
    class AdditionalViewHolder (val binding : AdditionalLayoutBinding) : RecyclerView.ViewHolder(binding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalViewHolder {
        //TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdditionalLayoutBinding.inflate(layoutInflater)
        return AdditionalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return  learnerItemList.size
    }

    override fun onBindViewHolder(holder: AdditionalViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val learnerItem = learnerItemList[position]
        val drosList = learnerItemList[0].dros?.indices
        if(drosList != null){
        for(i in drosList) {
            defHandlerClass.handlePhraseAndUsage(learnerItemList, i, holder)
            }
        }
        else{
            holder.binding.additionalLayout.isVisible = false
        }
    }
}