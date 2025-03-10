package com.ceocoding.cineaux.presentation.details

import com.ceocoding.cineaux.domain.model.FilmDetail

data class DetailsState(
    val isLoading: Boolean = false,
    val filmDetail: FilmDetail? = null,
    val error: String = ""
)
