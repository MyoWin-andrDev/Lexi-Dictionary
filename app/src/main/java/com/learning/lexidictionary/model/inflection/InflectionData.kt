package com.learning.lexidictionary.model.inflection

data class InflectionData(
    val id: String,
    val metadata: Metadata,
    val results: List<Result>
)