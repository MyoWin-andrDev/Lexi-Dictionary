package com.learning.lexidictionary.model.thesaurus

data class Thesaurus(
    val antonyms: List<String>,
    val synonyms: List<String>,
    val word: String
)