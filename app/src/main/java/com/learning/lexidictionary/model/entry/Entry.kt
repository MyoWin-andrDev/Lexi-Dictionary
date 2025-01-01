package com.learning.lexidictionary.model.entry

data class Entry(
    val etymologies: List<String>,
    val pronunciations: List<Pronunciation>,
    val senses: List<Sense>
)