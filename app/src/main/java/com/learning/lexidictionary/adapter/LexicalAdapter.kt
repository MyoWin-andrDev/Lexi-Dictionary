package com.learning.lexidictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.lexidictionary.databinding.LexiItemBinding
import com.learning.lexidictionary.model.learnerEdition.Meta

class LexicalAdapter (val lexicalList: List<Meta>) : RecyclerView.Adapter<LexicalAdapter.LexicalViewHolder>() {

    class LexicalViewHolder(val binding : LexiItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LexicalViewHolder {
        //TODO("Not yet implemented")
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LexiItemBinding.inflate(layoutInflater)
        return LexicalViewHolder(binding)
    }

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return lexicalList.size
    }

    override fun onBindViewHolder(holder: LexicalViewHolder, position: Int) {
        //TODO("Not yet implemented")
        val item = lexicalList[position]

    }
}