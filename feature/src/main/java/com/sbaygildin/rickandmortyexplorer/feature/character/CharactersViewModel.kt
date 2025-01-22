package com.sbaygildin.rickandmortyexplorer.feature.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sbaygildin.rickandmortyexplorer.data.repository.CharacterRepositoryImpl
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.domain.usecase.GetCharacterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repositoryImpl: CharacterRepositoryImpl,
    private val getCharacterUseCase: GetCharacterUseCase,
) : ViewModel() {


    val pagedCharactersFlow: Flow<PagingData<RMCharacter>> = repositoryImpl
        .getCharacterPageStream()
        .cachedIn(viewModelScope)

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
