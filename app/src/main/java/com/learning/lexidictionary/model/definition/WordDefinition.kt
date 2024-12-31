package com.learning.lexidictionary.model.definition

data class WordDefinition(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>,
    val word: String
)