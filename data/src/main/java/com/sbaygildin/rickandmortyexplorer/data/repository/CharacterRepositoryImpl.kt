package com.sbaygildin.rickandmortyexplorer.data.repository

import com.sbaygildin.rickandmortyexplorer.data.api.CharacterApi
import com.sbaygildin.rickandmortyexplorer.data.api.dto.toDomain
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository

class CharacterRepositoryImpl(private val api: CharacterApi) : CharacterRepository {
    private var nextPageUrl: String? = null

    override suspend fun getCharacters(page: Int): List<RMCharacter> {
        val response = api.getCharacters(page)
        nextPageUrl = response.info.next
        return response.results.map { it.toDomain() }

    }

    override suspend fun getCharacterById(id: Int): RMCharacter {
        return api.getCharacterById(id).toDomain()

    }
}