package com.learning.lexidictionary.model.inflection

data class Result(
    val id: String,
    val language: String,
    val lexicalEntries: List<LexicalEntry>,
    val text: String
)