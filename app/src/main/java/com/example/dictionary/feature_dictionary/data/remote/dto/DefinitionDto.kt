package com.example.dictionary.feature_dictionary.data.remote.dto

import com.example.dictionary.feature_dictionary.domain.model.Definition

data class DefinitionDto(
    val antonyms: List<Any>,
    val definition: String,
    val example: String?,
    val synonyms: List<Any>
) {
    fun toDefinition(): Definition {
        return Definition(antonyms, definition, example?:"", synonyms)
    }
}