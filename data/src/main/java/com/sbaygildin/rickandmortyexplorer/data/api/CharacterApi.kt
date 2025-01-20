package com.sbaygildin.rickandmortyexplorer.data.api

import com.sbaygildin.rickandmortyexplorer.data.api.dto.CharacterResponseDto
import com.sbaygildin.rickandmortyexplorer.domain.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponseDto
}