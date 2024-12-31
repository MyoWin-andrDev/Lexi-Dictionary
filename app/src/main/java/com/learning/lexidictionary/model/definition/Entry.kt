package com.learning.lexidictionary.model.definition

data class Entry(
    val etymologies: List<String>,
    val grammaticalFeatures: List<GrammaticalFeature>,
    val senses: List<Sense>,
    val variantForms: List<VariantForm>
)