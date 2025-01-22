package com.sbaygildin.rickandmortyexplorer.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sbaygildin.rickandmortyexplorer.data.api.CharacterApi
import com.sbaygildin.rickandmortyexplorer.data.api.dto.toDomain
import com.sbaygildin.rickandmortyexplorer.domain.model.RMCharacter

class CharacterPagingSource(
    private val api: CharacterApi,
) : PagingSource<Int, RMCharacter>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RMCharacter> {
        return try {
            val currentPage = params.key ?: 1
            val response = api.getCharacters(currentPage)
            val characters = response.results.map { it.toDomain() }
            val nextKey = if (response.info.next != null) currentPage + 1 else null
            LoadResult.Page(
                data = characters,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = nextKey
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RMCharacter>): Int? {
        return null
    }

}