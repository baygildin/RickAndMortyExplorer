package com.sbaygildin.rickandmortyexplorer.feature.character

import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter

data class CharactersUiState(
    val characters: List<RMCharacter> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
