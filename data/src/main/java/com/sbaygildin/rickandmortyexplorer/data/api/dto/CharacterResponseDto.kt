package com.sbaygildin.rickandmortyexplorer.data.api.dto

data class CharacterResponseDto(
    val info: PageInfoDto,
    val results: List<CharacterDto>,
)

data class PageInfoDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?,
)