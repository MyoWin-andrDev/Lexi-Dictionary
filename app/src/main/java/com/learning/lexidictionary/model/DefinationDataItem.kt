package com.learning.lexidictionary.model

data class DefinationDataItem(
    val def: List<Def>,
    val et: List<List<String>>,
    val fl: String,
    val hom: Int,
    val hwi: Hwi,
    val ins: List<In>,
    val lbs: List<String>,
    val meta: Meta,
    val shortdef: List<String>,
    val uros: List<Uro>
)