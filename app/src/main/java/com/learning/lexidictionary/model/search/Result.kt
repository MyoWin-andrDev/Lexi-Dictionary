package com.learning.lexidictionary.model.search

data class Result(
    val id: String,
    val label: String,
    val matchString: String,
    val matchType: String,
    val region: String,
    val score: Double,
    val word: String
)