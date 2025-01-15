package com.learning.lexidictionary.handler

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.learning.lexidictionary.adapter.AdditionalAdapter
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.data.Definition
@RequiresApi(Build.VERSION_CODES.O)
class UIHandler(val context : Context, val wordId : String) {
    private val stringHandler = StringHandler(wordId, context)
    fun setDefinition(def : String , index : Int,binding : DefinitionAdapter.DefinitionViewHolder){
        when(index){
            1 -> {
                binding.binding.senDefinition.text = stringHandler.replaceBCString(def)
                binding.binding.senDefinition.isVisible = true
            }
            2 -> {
                binding.binding.senShortDefinition.text = stringHandler.replaceBCString(def)
                binding.binding.senShortDefinition.isVisible = true
            }
            3 -> {
                binding.binding.subSenDefinition.text = stringHandler.replaceBCString(def)
                binding.binding.subSenDefinition.isVisible = true
            }
            in 4 .. 20 -> {
                binding.binding.subSenShortDefinition.text = stringHandler.replaceBCString(def)
                binding.binding.subSenShortDefinition.isVisible = true
            }
        }
    }
    fun setDefExample(t : String, index : Int, binding : DefinitionAdapter.DefinitionViewHolder){
        when(index){
            1 -> {
                binding.binding.senDefEg1.text = stringHandler.replaceBCString(t.replace("{it}","").replace("{/it}", ""))
                binding.binding.senDefEg1.isVisible = true
            }
            2 -> {
                binding.binding.senShortDefEg1.text = stringHandler.replaceBCString(t)
                binding.binding.senShortDefEg1.isVisible = true
            }
            3 -> {
                binding.binding.subSenDefEg1.text = stringHandler.replaceBCString(t)
                binding.binding.subSenDefEg1.isVisible = true
            }
            in 4 .. 20 -> {
                binding.binding.subSenShortDefEg1.text = stringHandler.replaceBCString(t)
                binding.binding.subSenShortDefEg1.isVisible = true
            }
        }
    }
    fun setPhrases(drp : String, index : Int, binding : AdditionalAdapter.AdditionalViewHolder){
        when(index){
            0 -> {
                binding.binding.drp1.text = drp
                binding.binding.drp1.isVisible = true
            }
            1 -> {
                binding.binding.drp2.text = drp
                binding.binding.drp2.isVisible = true
            }
            2 -> {
                binding.binding.drp3.text = drp
                binding.binding.drp3.isVisible = true
            }
            in 3..20 -> {
                binding.binding.drp4.text = drp
                binding.binding.drp4.isVisible = true
            }
        }
    }
    fun  setUsage(usage : String , index : Int , binding: AdditionalAdapter.AdditionalViewHolder){
        when(index){
            0 -> {
                binding.binding.usage1.text = usage
                binding.binding.usageLayout1.isVisible = true
            }
            1 -> {
                binding.binding.usage2.text = usage
                binding.binding.usageLayout2.isVisible = true
            }
            2 -> {
                binding.binding.usage3.text = usage
                binding.binding.usageLayout3.isVisible = true
            }
            3 -> {
                binding.binding.usage4.text = usage
                binding.binding.usageLayout4.isVisible = true
            }
        }
    }
    fun setPhraseExample(usage : String , index : Int , binding: AdditionalAdapter.AdditionalViewHolder){
        when(index){
            0 -> {
                binding.binding.eg1.text = usage
                binding.binding.eg1.isVisible = true
            }
            1 -> {
                binding.binding.eg2.text = usage
                binding.binding.eg2.isVisible = true
            }
            2 -> {
                binding.binding.eg3.text = usage
                binding.binding.eg3.isVisible = true
            }
            3 -> {
                binding.binding.eg4.text = usage
                binding.binding.eg4.isVisible = true
            }
        }
    }
}