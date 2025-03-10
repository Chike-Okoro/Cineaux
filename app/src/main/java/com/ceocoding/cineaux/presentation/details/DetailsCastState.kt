package com.ceocoding.cineaux.presentation.details

import com.ceocoding.cineaux.domain.model.Cast

data class DetailsCastState(
    val isLoading: Boolean = false,
    val cast: List<Cast> = emptyList(),
    val error: String = ""
)
