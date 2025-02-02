package com.learning.lexidictionary.adapter

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.databinding.SynonymItemBinding

class SynonymAdapter(val context : Context, val synonymList : List<String>) : RecyclerView.Adapter<SynonymAdapter.SynonymViewHolder>() {

    class SynonymViewHolder(val binding : SynonymItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SynonymViewHolder {
        //TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = SynonymItemBinding.inflate(layoutInflater)
        return SynonymViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return synonymList.size
    }

    override fun onBindViewHolder(holder: SynonymViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val synonymItem = synonymList[position]
        holder.binding.synoItem.text = synonymItem
    }
}