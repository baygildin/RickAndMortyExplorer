package com.sbaygildin.rickandmortyexplorer.data.repository

import com.sbaygildin.rickandmortyexplorer.data.api.CharacterApi
import com.sbaygildin.rickandmortyexplorer.data.api.dto.toDomain
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository

class CharacterRepositoryImpl(private val api: CharacterApi) : CharacterRepository {
    override suspend fun getCharacters(page: Int): List<RMCharacter> {
        return api.getCharacters(page).results.map { it.toDomain() }

    }
}