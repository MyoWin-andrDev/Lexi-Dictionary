package com.learning.lexidictionary.ui_handler

import android.annotation.SuppressLint
import android.util.Log
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.learning.lexidictionary.adapter.AdditionalAdapter
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.model.learnerEdition.Dro
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.model.learnerEdition.defDetail
import com.learning.lexidictionary.model.learnerEdition.dt

class DefinitionHandler {
    private val defClass = Definition()
    private val gson = Gson()
   // private val definitionHandler = DefinitionHandler()
    //Retrieving Data At Index
    @SuppressLint("SuspiciousIndentation")
    fun handleDefAndEg(learnerDataItem: List<LearnerDataItem>, index : Int, binding : DefinitionAdapter.DefinitionViewHolder){
        val sSEQIndices = learnerDataItem[0].def[0].sseq.indices
        val sSEQList = learnerDataItem[0].def[0].sseq[index][0] as List<*>
        //Decision Making of "sense" or "sen"
        val value = sSEQList[0] as String
        if(value == "sense"){
            val defDetail = Gson().fromJson(Gson().toJson(arrayOf(sSEQList[1])),Array<defDetail>::class.java).toList()
            val grammaticalLabel = defDetail[0].sgram //Non-Count
            val dtList = defDetail[0].dt
            dtList.indices.forEach { i ->
                val unknownList = dtList[i] as List<*>
                val unknownValue = unknownList[0].toString()
                //Setting Data to UI
                if(unknownValue == "text"){
                    val def = unknownList[1] as String
                    if(def.startsWith("{bc}")) {
                        //Definition
                        setDefinition(def, index, binding)
                    }
                }
                //Verbal Illustrations: vis
                else if (unknownValue == "vis"){
                    val visList = unknownList[1] as List<*>
                    val exampleList = Definition().linkedTreeMapToEgList(visList)
                    Log.d("exampleT", exampleList[0].t)
                    setExample(exampleList[0].t, index, binding)
                }
                // Usage Notes: uns
                else if (unknownValue == "uns") {
                    val unsItem = unknownList[1] as List<*>
                    val unsFirstIndex = unsItem[0] as List<*>
                    val textArray = unsFirstIndex[0] as List<String>// there is two values in it
                    if( textArray[0] == "text"){
                        Log.d("usage", textArray[1])
                    }
                    val vis = unsFirstIndex[1] as List<*>
                    if( vis[0] == "vis"){
                        val tList = Definition().linkedTreeMapToEgList(vis[1] as List<*>)
                        Log.d("t", tList[0].t)//Vis Text
                    }
                }
                //Supplemental Information Note: snote
                else if( unknownValue == "snote"){
                    val sNoteList = unknownList[1] as List<*>
                    val sNoteItem = sNoteList[1] as List<*>
                    val stringValue = sNoteItem[0] as String
                    if( stringValue == "t"){
                        Log.d("snoteItem", sNoteItem[1].toString())//
                    }
                    else if (stringValue == "vis"){
                        val sNoteVisList = defClass.linkedTreeMapToEgList(sNoteItem[1] as List<*>)
                        Log.d("snoteVisList", sNoteVisList[0].t.toString())//t Value
                    }
                }
            }
        }
        else if(value == "sen"){

        }
    }
    //Defined Run-Ons: dros
    @SuppressLint("SuspiciousIndentation")
    fun handlePhraseAndUsage(learnerDataItem: List<LearnerDataItem>, index: Int, binding: AdditionalAdapter.AdditionalViewHolder){
        val dros = learnerDataItem[0].dros as List<Dro>
        val drosList = dros[0].drp
        //Set Phrases
        dros.indices.forEach { i ->
            setPhrases(dros[i].drp, i , binding)

        }
    }

    fun getMainDef(learnerDataItem: List<LearnerDataItem>, binding : ActivityDefinationBinding){
        val shortDef = learnerDataItem[0].shortdef[0]
        binding.definitionText1.text = shortDef.substringAfter("{bc}").substringBefore("—")
    }
    //Set Def in UI
    private fun setDefinition(def : String , index : Int,binding : DefinitionAdapter.DefinitionViewHolder){
        when(index){
            1 -> {
                binding.binding.senDefinition.text = defClass.replaceBCString(def)
                binding.binding.senDefinition.isVisible = true
            }
            2 -> {
                binding.binding.senShortDefinition.text = defClass.replaceBCString(def)
                binding.binding.senShortDefinition.isVisible = true
            }
            3 -> {
                binding.binding.subSenDefinition.text = defClass.replaceBCString(def)
                binding.binding.subSenDefinition.isVisible = true
            }
            in 4 .. 20 -> {
                binding.binding.subSenShortDefinition.text = defClass.replaceBCString(def)
                binding.binding.subSenShortDefinition.isVisible = true
            }
        }
    }
    private fun setExample(t : String, index : Int, binding : DefinitionAdapter.DefinitionViewHolder){
        when(index){
            1 -> {
                binding.binding.senDefEg1.text = defClass.replaceBCString(t)
                binding.binding.senDefEg1.isVisible = true
            }
            2 -> {
                binding.binding.senShortDefEg1.text = defClass.replaceBCString(t)
                binding.binding.senShortDefEg1.isVisible = true
            }
            3 -> {
                binding.binding.subSenDefEg1.text = defClass.replaceBCString(t)
                binding.binding.subSenDefEg1.isVisible = true
            }
            in 4 .. 20 -> {
                binding.binding.subSenShortDefEg1.text = defClass.replaceBCString(t)
                binding.binding.subSenShortDefEg1.isVisible = true
            }
        }
    }
    private fun setPhrases(drp : String, index : Int, binding : AdditionalAdapter.AdditionalViewHolder){
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
    private fun setUsage(usage : String , index : Int , binding: AdditionalAdapter.AdditionalViewHolder){
        when(index){
            0 -> {
                binding.binding.usage1.text = usage
                binding.binding.usage1.isVisible = true
            }
            1 -> {
                binding.binding.usage2.text = usage
                binding.binding.usage2.isVisible = true
            }
            2 -> {
                binding.binding.usage3.text = usage
                binding.binding.usage3.isVisible = true
            }
            3 -> {
                binding.binding.usage4.text = usage
                binding.binding.usage4.isVisible = true
            }
        }
    }

    fun retrieveUsageLogic(drosList : List<Dro>, index : Int){
        val dtList = drosList[1].def[0].sseq[index][0] as List<*>
        if(dtList[0] == "sense"){
            val senseItem = defClass.linkedTreeMapToSenseList(arrayOf(dtList[1]))
            if(senseItem[0].dt != null) {
                val dtItem = senseItem[0].dt[0] as List<*>
                val unknownValue = dtItem[0] as String
                if( unknownValue == "text"){
                    Log.d("dtDx", dtItem[1].toString()) // dx
                }
                if( unknownValue == "uns"){
                    val unsItem = dtItem[1] as List<*>
                    val unsFirstIndex = unsItem[0] as List<*>
                    val textArray = unsFirstIndex[0] as List<String>// there is two values in it
                    if( textArray[0] == "text"){
                        Log.d("usage", textArray[1])
                    }
                    val vis = unsFirstIndex[1] as List<*>
                    if( vis[0] == "vis"){
                        val tList = Definition().linkedTreeMapToEgList(vis[1] as List<*>)
                        Log.d("t", tList[0].t)//Vis Text
                    }
                }
            }
        }
    }

}