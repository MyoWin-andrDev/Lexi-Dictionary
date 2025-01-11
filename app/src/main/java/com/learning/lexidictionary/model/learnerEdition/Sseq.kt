package com.learning.lexidictionary.model.learnerEdition

data class Eg(
    val t : String
)
data class defDetail(
    val sn : String,
    val sgram : String,
    val dt : List<*>
)
//Unofficial DefList
data class DefinitionDetail(
    val textList : List<Any>,
    val visList : Any,
    val uns : String
)

data class uns(
    val unsList : List<List<unsItem>>
)

data class unsItem(
    val textList : List<String>,
    val visList : List<Eg>
)
