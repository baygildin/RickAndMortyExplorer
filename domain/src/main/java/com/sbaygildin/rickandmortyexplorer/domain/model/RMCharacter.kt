package com.sbaygildin.rickandmortyexplorer.domain.model

data class CharacterResponse(
    val results: List<RMCharacter>
)

data class RMCharacter(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val locationName: String,
    val locationUrl: String
)
