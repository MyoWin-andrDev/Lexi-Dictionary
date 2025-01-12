package com.learning.lexidictionary.ui_handler

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import com.google.gson.Gson
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.databinding.ActivityDefinationBinding
import com.learning.lexidictionary.model.learnerEdition.DefinitionDetail
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.model.learnerEdition.defDetail

class DefinitionHandler {
    private val defClass = Definition()
    //Retrieving Data At Index
    @SuppressLint("SuspiciousIndentation")
    fun handleDefAndEg(learnerDataItem: List<LearnerDataItem>, index : Int, binding : DefinitionAdapter.PhrasesViewHolder){

        val sSEQList = learnerDataItem[0].def[0].sseq[index][0] as List<*>
        //Decision Making of "sense" or "sen"
        val value = sSEQList[0] as String
        if(value == "sense"){
            val defDetail = Gson().fromJson(Gson().toJson(arrayOf(sSEQList[1])),Array<defDetail>::class.java).toList()
                Log.d("defDetail", defDetail.toString())
            val grammaticalLabel = defDetail[0].sgram //Non-Count
            val dtList = defDetail[0].dt
            val unknownList = dtList[0] as List<*>
            val unknownValue = unknownList[0].toString()
            //Setting Data to UI
            if(unknownValue == "text"){
                val def = unknownList[1] as String
                if(def.startsWith("{bc}")){
                    //Definition
                    binding.binding.senDefinition.text =defClass.replaceBCString(def.toString())
                }
                else if(def.startsWith("{dx}")){
                    //Hierarchical Context
                }
                else{

                }

            }
            //Verbal Illustrations: vis
            else if (unknownValue == "vis"){
                val visList = unknownList[1] as List<*>
                val exampleList = Definition().linkedTreeMapToList(visList)
                binding.binding.senDefEg1.text = exampleList[0].t
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
                    val tList = Definition().linkedTreeMapToList(vis[1] as List<*>)
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
                    val sNoteVisList = defClass.linkedTreeMapToList(sNoteItem[1] as List<*>)
                    Log.d("snoteVisList", sNoteVisList[0].t.toString())//t Value
                }
            }


        }
        else if(value == "sen"){

        }
    }

    fun getMainDef(learnerDataItem: List<LearnerDataItem>, binding : ActivityDefinationBinding){
        val shortDef = learnerDataItem[0].shortdef[0]
        binding.definitionText1.text = shortDef.substringAfter("{bc}")
    }

}