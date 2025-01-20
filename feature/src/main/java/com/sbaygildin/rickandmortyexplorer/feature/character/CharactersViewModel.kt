package com.sbaygildin.rickandmortyexplorer.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbaygildin.rickandmortyexplorer.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(

    private val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState

    fun loadCharacters(page: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val characters = getCharacterUseCase(page)
                _uiState.value = CharactersUiState(characters = characters)
            } catch (e: Exception) {
                _uiState.value = CharactersUiState(error = e.message)
            }
        }
    }
}