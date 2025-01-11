package com.learning.lexidictionary.ui_handler

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.learning.lexidictionary.adapter.DefinitionAdapter
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.model.learnerEdition.DefinitionDetail
import com.learning.lexidictionary.model.learnerEdition.Eg
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.model.learnerEdition.defDetail
import com.learning.lexidictionary.model.learnerEdition.uns
import com.learning.lexidictionary.model.learnerEdition.unsItem

class DefinitionHandler {
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
            val dtList = defDetail[0].dt as List<*>
            var unknownList = dtList[2] as List<*>
            val unknownValue = unknownList[0].toString()
            //Setting Data to UI
            if(unknownValue == "text"){
                val def = unknownList[1]
                Log.d("def",def.toString())
                binding.binding.senDefinition.text =Definition().replaceBCString(def.toString())
            }
            else if (unknownValue == "vis"){
                val visList = unknownList[1] as List<*>
                val exampleList = Definition().linkedTreeMapToList(visList)
                binding.binding.senDefEg1.text = exampleList[0].t
            }
            else if (unknownValue == "uns") {
                val unsItem = unknownList[1] as List<*>
                val unsFirstIndex = unsItem[0] as List<*>
                val textArray = unsFirstIndex[0] as List<String>// there is two value in it
                val vis = unsFirstIndex[1] as List<*>
                if( vis[0] == "vis"){
                    val tList = Definition().linkedTreeMapToList(vis[1] as List<*>)
                    Log.d("t", tList[0].t)//Vis Text
                }
            }


        }
        else if(value == "sen"){

        }
    }

    fun gettingOfficialDefList(definitionDetailList : List<DefinitionDetail>){

    }
}