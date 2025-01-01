package com.learning.lexidictionary.model.word

data class Pronunciation(
    val audioFile: String,
    val dialects: List<String>,
    val phoneticNotation: String,
    val phoneticSpelling: String
)