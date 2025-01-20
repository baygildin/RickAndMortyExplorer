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
    val location: LocationDto,
)

data class LocationDto(
    val name: String,
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
        location = location.name
    )
}
fun CharacterResponseDto.toDomain(): CharacterResponse {
    return CharacterResponse(
        results = results.map { it.toDomain() }
    )
}