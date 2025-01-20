package com.sbaygildin.rickandmortyexplorer.domain.usecase

import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter

class GetCharacterUseCase (private val repository: CharacterRepository) {
    suspend operator fun invoke(page: Int): List<RMCharacter>{
        return repository.getCharacters(page)
    }
}