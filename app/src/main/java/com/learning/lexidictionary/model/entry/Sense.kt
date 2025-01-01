package com.learning.lexidictionary.model.entry

data class Sense(
    val definitions: List<String>,
    val domainClasses: List<DomainClasse>,
    val id: String,
    val notes: List<Note>,
    val semanticClasses: List<SemanticClasse>,
    val shortDefinitions: List<String>,
    val subsenses: List<Subsense>,
    val variantForms: List<VariantForm>
)