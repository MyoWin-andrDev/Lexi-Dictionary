package com.learning.lexidictionary.model.entries

data class EntriesData(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>,
    val word: String
)