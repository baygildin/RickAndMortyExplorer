package com.sbaygildin.rickandmortyexplorer.domain.repository

import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter

interface CharacterRepository {
    suspend fun getCharacters(page: Int): List<RMCharacter>
    suspend fun getCharacterById(id: Int): RMCharacter
}