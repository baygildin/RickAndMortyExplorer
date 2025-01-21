package com.sbaygildin.rickandmortyexplorer.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
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

    private var currentPage = 1
    private var isLastPage = false
    private var isLoadingMore = false

    fun loadCharacters(page: Int = 1) {
        if (isLoadingMore || isLastPage) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val characters = getCharacterUseCase(page)
                if (characters.isEmpty()) {
                    isLastPage = true
                } else {
                    currentPage++
                    val updatedCharacters = _uiState.value.characters + characters
                    _uiState.value = _uiState.value.copy(characters = updatedCharacters)
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
                isLoadingMore = false
            }
        }
    }

    fun loadNextPage() {
        if (isLoadingMore || isLastPage) {
            return
        }

        isLoadingMore = true
        viewModelScope.launch {
            try {
                val newCharacters = getCharacterUseCase(currentPage)
                if (newCharacters.isEmpty()) {
                    isLastPage = true
                } else {
                    currentPage++
                    _uiState.value = _uiState.value.copy(
                        characters = _uiState.value.characters + newCharacters
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(error = e.message)
            } finally {
                isLoadingMore = false
            }
        }
    }

    fun getCharacterById(id: Int, onResult: (RMCharacter?) -> Unit) {
        viewModelScope.launch {
            try {
                val character = getCharacterUseCase.getCharacterById(id)
                onResult(character)
            } catch (e: Exception) {
                onResult(null)
            }
        }
    }
}

