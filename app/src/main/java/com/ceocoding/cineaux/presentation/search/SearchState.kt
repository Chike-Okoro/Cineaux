package com.ceocoding.cineaux.presentation.search

import com.ceocoding.cineaux.domain.model.FilmData

data class SearchState(
    val films: List<FilmData> = emptyList(),
    val isSearching: Boolean = false,
    val query: String = "",
    val isHintVisible: Boolean = false
)
