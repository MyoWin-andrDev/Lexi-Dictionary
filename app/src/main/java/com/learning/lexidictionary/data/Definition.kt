package com.learning.lexidictionary.data

import android.util.Log
import com.google.gson.Gson
import com.learning.lexidictionary.model.learnerEdition.Dro
import com.learning.lexidictionary.model.learnerEdition.Eg
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem
import com.learning.lexidictionary.model.learnerEdition.senseItem

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
    fun linkedTreeMapToSenseList(array : Array<*>) : List<senseItem>{
        val gson = Gson()
        val formattedList = gson.fromJson(gson.toJson(array), Array<senseItem>::class.java).toList()
        return  formattedList
    }
}