package com.learning.lexidictionary.model.learnerEdition

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("app-shortdef")
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