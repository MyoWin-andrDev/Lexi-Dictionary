package com.learning.lexidictionary.model.definition

data class WordDefinition(
    val metadata: Metadata,
    val query: String,
    val results: List<Result>
)