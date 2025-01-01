package com.learning.lexidictionary.model.sentenceData

data class LexicalEntry(
    val language: String,
    val lexicalCategory: LexicalCategory,
    val sentences: List<Sentence>,
    val text: String
)