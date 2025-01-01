package com.learning.lexidictionary.model.entries

data class Pronunciation(
    val audioFile: String,
    val dialects: List<String>,
    val phoneticNotation: String,
    val phoneticSpelling: String
)