package com.ceocoding.cineaux.domain.model

data class FilmData(
    val id: Int,
    val backdropPath: String?,
    val title: String,
    val voteAverage: Double,
    val releaseDate: String,
    val genres: List<GenreType>
)
