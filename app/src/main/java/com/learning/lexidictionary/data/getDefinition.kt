package com.learning.lexidictionary.data

import android.util.Log
import com.learning.lexidictionary.model.learnerEdition.Def
import com.learning.lexidictionary.model.learnerEdition.LearnerDataItem

class getDefinition {
    fun getDefinition(learnerDataItem: LearnerDataItem){
        val sseqList = learnerDataItem.def[0].sseq[0][0] as List<*>
        //Retrieve the sense and the detail
        val sense = sseqList[0] as String
        val detail = sseqList[1] as Map<*, *>
        //Extract dt as List
        val dt = detail["dt"] as List<*>
        Log.d("sense", sense)
        Log.d("detail", detail.toString())
        Log.d("dt", dt.toString())
    }
}