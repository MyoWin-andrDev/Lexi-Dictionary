package com.learning.lexidictionary.model.search

data class Metadata(
    val limit: String,
    val offset: String,
    val operation: String,
    val provider: String,
    val schema: String,
    val sourceLanguage: String,
    val total: String
)