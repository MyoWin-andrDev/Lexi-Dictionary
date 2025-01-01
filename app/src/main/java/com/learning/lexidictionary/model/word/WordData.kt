package com.learning.lexidictionary.model.word

data class WordData(
    val metadata: Metadata,
    val query: String,
    val results: List<Result>
)