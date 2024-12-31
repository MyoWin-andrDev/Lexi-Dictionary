package com.learning.lexidictionary.model.definition

data class Pronunciation(
    val audioFile: String,
    val dialects: List<String>,
    val phoneticNotation: String,
    val phoneticSpelling: String
)