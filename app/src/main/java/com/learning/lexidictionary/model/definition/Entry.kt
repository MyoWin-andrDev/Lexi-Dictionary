package com.learning.lexidictionary.model.definition

data class Entry(
    val etymologies: List<String>,
    val inflections: List<Inflection>,
    val pronunciations: List<Pronunciation>,
    val senses: List<Sense>
)