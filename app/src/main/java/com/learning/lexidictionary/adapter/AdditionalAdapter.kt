package com.learning.lexidictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.AdditionalLayoutBinding
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.ui_handler.DefinitionHandler

class AdditionalAdapter(val context : Context, val learnerItemList : List<LearnerDataItem>) : RecyclerView.Adapter<AdditionalAdapter.AdditionalViewHolder>() {
    private val defClass = Definition()
    private val defHandlerClass = DefinitionHandler()
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
        defHandlerClass.handlePhraseAndUsage(learnerItemList, 0, holder)
    }
}