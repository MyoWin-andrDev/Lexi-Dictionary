package com.learning.lexidictionary.model.sentenceData

data class SentenceData(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>,
    val word: String
)