package com.learning.lexidictionary.model.entries

data class Subsense(
    val definitions: List<String>,
    val domainClasses: List<DomainClasse>,
    val id: String,
    val semanticClasses: List<SemanticClasse>,
    val shortDefinitions: List<String>
)