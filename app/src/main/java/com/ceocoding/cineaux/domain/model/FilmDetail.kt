package com.ceocoding.cineaux.domain.model

data class FilmDetail(
    val id: Int,
    val backdropPath: String?,
    val genres: List<String>,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val voteAverage: Double,
    val releaseDate: String
)
