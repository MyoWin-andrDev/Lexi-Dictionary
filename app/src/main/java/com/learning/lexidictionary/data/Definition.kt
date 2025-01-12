package com.learning.lexidictionary.data

import android.util.Log
import com.google.gson.Gson
import com.learning.lexidictionary.model.learnerEdition.Dro
import com.learning.lexidictionary.model.learnerEdition.Eg
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem

class Definition {
    fun getDefinition(learnerDataItem: List<LearnerDataItem>) : List<*>{
        val sseqList = learnerDataItem[0].def[0].sseq[0][0] as List<*>
        //Retrieve the sense and the detail
        val sense = sseqList[0] as String
        val detail = sseqList[1] as Map<*, *>
        Log.d("detail", detail.toString())
        //Extract dt as List
        val dt = detail["dt"] as List<*>
        Log.d("dt", dt.toString())
        return  dt
    }

    fun getDefinitionAtIndex(learnerDataItem: List<LearnerDataItem>, index : Int) : List<*>? {
        val sseqList = learnerDataItem[0].def[0].sseq[index][0] as List<*>
        //Retrieve the sense and the detail
        val sense = sseqList[0] as String
        var dt : List<*>? = null
        if(sense == "sense"){
        val detail = sseqList[1] as Map<*, *>
        //Extract dt as List
        dt = detail["dt"] as List<*>
        Log.d("dt", dt.toString())
        }
        return  dt

    }
//    fun getExampleAtIndex(learnerDataItem: List<LearnerDataItem>, index : Int) : List<*>{
//        val sseqList = learnerDataItem[0].def[0].sseq[index][0] as List<*>
//    }

    fun replaceBCList( list : List<String>) : List<String> {
        val replacedList = list.map { list ->
            val occurrence = list.split("{bc}").size - 1
            when (occurrence) {
                0 -> list
                1 -> list.replace("{bc}", "\u2022 ")
                else -> list.replaceFirst("{bc}","\u2022").replaceFirst("{bc}","\n\u2022")
            }
        }
        return replacedList
    }

    fun replaceBCString( string : String) : String {
        val occurrence = string.split("{bc}").size - 1
       return when (occurrence) {
            0 -> string
            1 -> string.replace("{bc}", "\u2022 ")
            else -> string.replaceFirst("{bc}","\u2022").replaceFirst("{bc}","\n\u003a")
        }
    }

    fun linkedTreeMapToEgList(list : List<*>) : List<Eg>{
        val gson = Gson()
        val formattedList = gson.fromJson(gson.toJson(list), Array<Eg>::class.java).toList()
        return formattedList
    }
    fun linkedTreeMapToDroList(list : List<*>) : List<Dro>{
        val gson = Gson()
        val formattedList = gson.fromJson(gson.toJson(list), Array<Dro>::class.java).toList()
        return  formattedList
    }
}