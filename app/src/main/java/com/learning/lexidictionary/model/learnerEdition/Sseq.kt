package com.learning.lexidictionary.model.learnerEdition

data class Definition(
    val term: String,
    val details: Details
)

data class Null(
    val sense : Any?,
    val definition : List<Details>
)

data class Details(
    val sn: String,
    val sgram: String,
    val dt: List<DefinitionDetail>
)

data class DefinitionDetail(
    val text: String? = null,
    val vis: List<Visual>? = null,
    val uns: List<List<UncertaintyDetail>>? = null
)

data class Visual(
    val t: String
)

data class UncertaintyDetail(
    val text: String? = null,
    val vis: List<Visual>? = null
)
