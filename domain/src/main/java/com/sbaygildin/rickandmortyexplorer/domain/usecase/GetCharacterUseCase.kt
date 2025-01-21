package com.sbaygildin.rickandmortyexplorer.domain.usecase

import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository

class GetCharacterUseCase (private val repository: CharacterRepository) {
    suspend operator fun invoke(page: Int): List<RMCharacter>{
        return repository.getCharacters(page)
    }

    suspend fun getCharacterById(id: Int): RMCharacter {
        return repository.getCharacterById(id)
    }
}