package com.learning.lexidictionary.model.entries

data class Entry(
    val etymologies: List<String>,
    val pronunciations: List<Pronunciation>,
    val senses: List<Sense>
)