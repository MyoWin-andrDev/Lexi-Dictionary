package com.learning.lexidictionary.model.learnerEdition

data class Meta(
    val appShortDef: AppShortdef,
    val highlight: String,
    val id: String,
    val offensive: Boolean,
    val section: String,
    val src: String,
    val stems: List<String>,
    val target: Target,
    val uuid: String
)