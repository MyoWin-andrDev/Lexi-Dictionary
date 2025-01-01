package com.learning.lexidictionary.model.inflection

data class LexicalEntry(
    val inflections: List<Inflection>,
    val language: String,
    val lexicalCategory: LexicalCategory
)