package com.sbaygildin.rickandmortyexplorer.data.api

import com.sbaygildin.rickandmortyexplorer.data.api.dto.CharacterDto
import com.sbaygildin.rickandmortyexplorer.data.api.dto.CharacterResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApi {
    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponseDto


    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterDto
}