package com.learning.lexidictionary.model.definition

data class LexicalEntry(
    val entries: List<Entry>,
    val language: String,
    val lexicalCategory: LexicalCategory,
    val text: String
)