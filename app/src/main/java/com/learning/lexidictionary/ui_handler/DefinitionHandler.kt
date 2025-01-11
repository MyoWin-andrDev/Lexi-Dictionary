package com.learning.lexidictionary.ui_handler

import android.annotation.SuppressLint
import android.util.Log
import com.google.gson.Gson
import com.learning.lexidictionary.data.Definition
import com.learning.lexidictionary.model.learnerEdition.DefinitionDetail
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.model.learnerEdition.defDetail

class DefinitionHandler {
    //Retrieving Data At Index
    @SuppressLint("SuspiciousIndentation")
    fun handleDefAndEg(learnerDataItem: List<LearnerDataItem>, index : Int){
        val sSEQList = learnerDataItem[0].def[0].sseq[index][0] as List<*>
        //Decision Making of "sense" or "sen"
        val value = sSEQList[0] as String
        if(value == "sense"){
            val defDetail = Gson().fromJson(Gson().toJson(arrayOf(sSEQList[1])),Array<defDetail>::class.java).toList()
                Log.d("defDetail", defDetail.toString())
            val grammaticalLabel = defDetail[0].sgram //Non-Count
            val dtList = defDetail[0].dt as List<*>
            var unknownList = dtList[0] as List<*>
            val unknownValue = unknownList[0].toString()
            //Setting Data to UI
            if(unknownValue == "text"){
                val def = unknownList[0]
                //binding.senDef.text = Definition().replacebc(def)
            }
            else if (unknownValue == "vis"){

            }
            else if (unknownValue == "uns"){

            }

        }
        else if(value == "sen"){

        }
    }

    fun gettingOfficialDefList(definitionDetailList : List<DefinitionDetail>){

    }
}