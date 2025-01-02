package com.learning.lexidictionary.model.learnerEdition

data class LearnerDataItem(
    val def: List<Def>,
    val dros: List<Dro>,
    val fl: String,
    val gram: String,
    val hom: Int,
    val hwi: Hwi,
    val ins: List<In>,
    val lbs: List<String>,
    val meta: Meta,
    val shortdef: List<String>,
    val uros: List<Uro>
)