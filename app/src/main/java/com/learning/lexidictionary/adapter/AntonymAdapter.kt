package com.learning.lexidictionary.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.databinding.AntonymItemBinding

class AntonymAdapter(val context : Context, val antonymList : List<String>) : RecyclerView.Adapter<AntonymAdapter.AntonymViewHolder>() {

    class AntonymViewHolder(val binding : AntonymItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AntonymViewHolder {
       // TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AntonymItemBinding.inflate(layoutInflater)
        return AntonymViewHolder(binding)
    }

    override fun getItemCount(): Int {
       // TODO("Not yet implemented")
        return antonymList.size
    }

    override fun onBindViewHolder(holder: AntonymViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val antonymItem = antonymList[position]
        holder.binding.antoItem.text = antonymItem
    }
}