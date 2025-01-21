package com.sbaygildin.rickandmortyexplorer.data.api.dto

import com.sbaygildin.rickandmortyexplorer.domain.model.CharacterResponse
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter

data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val image: String,
    val episode: List<String>,
    val location: LocationDto,
)

data class LocationDto(
    val name: String,
    val url: String
)

fun CharacterDto.toDomain(): RMCharacter {
    return RMCharacter(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        image = image,
        episode= episode,
        locationName = location.name,
        locationUrl = location.url
    )
}
fun CharacterResponseDto.toDomain(): CharacterResponse {
    return CharacterResponse(
        results = results.map { it.toDomain() },
        nextPage = info.next
    )
}