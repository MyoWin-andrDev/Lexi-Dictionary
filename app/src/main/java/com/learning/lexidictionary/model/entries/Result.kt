package com.learning.lexidictionary.model.entries

data class Result(
    val id: String,
    val language: String,
    val lexicalEntries: List<LexicalEntry>,
    val type: String,
    val word: String
)