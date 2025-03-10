package com.ceocoding.cineaux.presentation.home

import com.ceocoding.cineaux.domain.model.FilmData

data class HomeState(
    val isLoading: Boolean = false,
    val films: List<FilmData> = emptyList(),
    val error: String = ""
)
