package com.learning.lexidictionary.model.studentEdition

data class StudentDataItem(
    val def: List<Def>,
    val dros: List<Dro>,
    val et: List<List<String>>,
    val fl: String,
    val hom: Int,
    val hwi: Hwi,
    val meta: Meta,
    val shortdef: List<String>,
    val uros: List<Uro>
)