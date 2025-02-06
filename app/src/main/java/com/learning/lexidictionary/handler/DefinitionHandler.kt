package com.learning.lexidictionary.handler

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.O)
class DefinitionHandler(val context : Context, val wordId : String) {
    private val defClass = Definition()
    private val uiHandler = UIHandler(context, wordId)
    //Retrieving Data At Index
    @SuppressLint("SuspiciousIndentation")
    fun handleDefAndEg(learnerDataItem: List<LearnerDataItem>, index : Int, binding : DefinitionAdapter.DefinitionViewHolder){
        //Set Gerund
        val gerundId = learnerDataItem[0].fl
        binding.binding.gerundId.text = gerundId.capitalize()
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
                when(unknownValue){
                    //Definition
                    "text" -> {
                        val def = unknownList[1] as String
                        if(def.startsWith("{bc}")) {
                            uiHandler.setDefinition(def, index, binding)
                        }}
                    //Verbal Illustrations: vis
                    "vis" -> {
                        val visList = unknownList[1] as List<*>
                        val exampleList = Definition().linkedTreeMapToEgList(visList)
                        Log.d("visExample", exampleList[0].t)
                        uiHandler.setDefExample(exampleList[0].t, index, binding)
                    }
                    "uns" -> {
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
                    "snote" -> {
                        val sNoteList = unknownList[1] as List<*>
                        val sNoteItem = sNoteList[1] as List<*>
                        val stringValue = sNoteItem[0] as String
                        if( stringValue == "t"){
                            Log.d("snoteItem", sNoteItem[1].toString())//
                        }
                        else if (stringValue == "vis"){
                            val sNoteVisList = defClass.linkedTreeMapToEgList(sNoteItem[1] as List<*>)
                            Log.d("snoteVisList", sNoteVisList[0].t)//t Value
                        }
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
        val dros = learnerDataItem[0].dros as? List<Dro>
        val drosList = dros?.get(index)?.drp
        //Set Phrases
        dros?.indices?.forEach { i ->
            uiHandler.setPhrases(dros[i].drp, i , binding)
            retrieveUsageLogic(dros,i , binding)
        }
    }

    fun getMainDef(learnerDataItem: List<LearnerDataItem>, binding : ActivityDefinationBinding){
        val shortDef = learnerDataItem[0].shortdef[0]
        binding.definitionText1.text = shortDef.substringAfter("{bc}").substringBefore("—")
    }

    @SuppressLint("SuspiciousIndentation")
    fun retrieveUsageLogic(drosList : List<Dro>, index : Int, binding: AdditionalAdapter.AdditionalViewHolder) {
        val dtList = drosList[index].def[0].sseq[0][0] as List<*>
        if (dtList[0] == "sense") {
            val senseItem = defClass.linkedTreeMapToSenseList(arrayOf(dtList[1]))
            //Subject/Status Labels: sls
            if (senseItem[0].sls != null) {
                uiHandler.setSLS(senseItem[0].sls[0], index, binding)
            }
            if (senseItem[0].dt != null) {
                val dtItem = senseItem[0].dt
                dtItem.indices.forEach { i ->
                    val unknownList = dtItem[i] as List<*>
                    val unknownValue = unknownList[0] as String
                    when (unknownValue) {
                        "text" -> {
                            if (unknownList[1].toString().startsWith("{dx}see")) {
                                uiHandler.setPhraseGuidance(unknownList[1].toString(), index, binding)
                            } else if (unknownList[1].toString().startsWith("{bc}")) {
                                uiHandler.setPhraseDef(unknownList[1].toString(), index, binding)
                            }
                        }

                        "vis" -> {
                            val visList = unknownList[1] as List<*>
                            val exampleList = Definition().linkedTreeMapToEgList(visList)
                            Log.d("visExample", exampleList[0].t)
                            Log.d("drosList", drosList[index].drp)
                            uiHandler.setPhraseExample(
                                exampleList[0].t,
                                drosList[index].drp,
                                index,
                                binding
                            )
                        }

                        "uns" -> {
                            val unsItem = unknownList[1] as List<*>
                            val unsFirstIndex = unsItem[0] as List<*>
                            val textArray =
                                unsFirstIndex[0] as List<String>// there is two values in it
                            if (textArray[0] == "text") {
                                uiHandler.setUsage(textArray[1], index, binding)
                            }
                            if (unsFirstIndex.size !== 1) {
                                val vis = unsFirstIndex[1] as List<*>
                                if (vis[0] == "vis") {
                                    val tList =
                                        Definition().linkedTreeMapToEgList(vis[1] as List<*>)
                                    uiHandler.setPhraseExample(
                                        tList[0].t,
                                        drosList[index].drp,
                                        index,
                                        binding
                                    )
                                }
                            }
                        }

//                        "sonte" -> {
//                            val snote =
//                        }
                    }
                }
            }
        }
    }
}