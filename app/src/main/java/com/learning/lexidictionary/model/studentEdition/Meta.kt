package com.learning.lexidictionary.model.studentEdition

data class Meta(
    val id: String,
    val offensive: Boolean,
    val section: String,
    val src: String,
    val stems: List<String>,
    val uuid: String
)