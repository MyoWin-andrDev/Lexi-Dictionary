package com.learning.lexidictionary.model.entry

data class EntriesData(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>,
    val word: String
)