package com.learning.lexidictionary.model.definition

data class Sense(
    val crossReferenceMarkers: List<String>,
    val crossReferences: List<CrossReference>,
    val id: String,
    val regions: List<Region>,
    val registers: List<Register>
)