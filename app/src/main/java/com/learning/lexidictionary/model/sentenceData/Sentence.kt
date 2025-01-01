package com.learning.lexidictionary.model.sentenceData

data class Sentence(
    val regions: List<Region>,
    val senseIds: List<String>,
    val text: String
)