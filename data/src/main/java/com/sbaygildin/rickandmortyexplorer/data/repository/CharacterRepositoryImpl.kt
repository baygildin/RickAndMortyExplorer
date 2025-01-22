package com.sbaygildin.rickandmortyexplorer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sbaygildin.rickandmortyexplorer.data.api.CharacterApi
import com.sbaygildin.rickandmortyexplorer.data.api.dto.toDomain
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter
import com.sbaygildin.rickandmortyexplorer.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(private val api: CharacterApi) :
    CharacterRepository {
    private var nextPageUrl: String? = null

    override suspend fun getCharacters(page: Int): List<RMCharacter> {
        val response = api.getCharacters(page)
        nextPageUrl = response.info.next
        return response.results.map { it.toDomain() }

    }

    override suspend fun getCharacterById(id: Int): RMCharacter {
        return api.getCharacterById(id).toDomain()

    }

    fun getCharacterPageStream(): Flow<PagingData<RMCharacter>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }
        ).flow
    }
}