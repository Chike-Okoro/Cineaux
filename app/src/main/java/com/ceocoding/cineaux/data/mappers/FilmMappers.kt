package com.ceocoding.cineaux.data.mappers

import com.ceocoding.cineaux.data.local.entity.WatchListEntity
import com.ceocoding.cineaux.data.remote.dto.*
import com.ceocoding.cineaux.domain.model.*

fun FilmDto.toFilmList(): FilmList {
    return FilmList(
        films = films.map { it.toFilmData() }
    )
}

fun FilmDetailDto.toFilmDetail(): FilmDetail {
    return FilmDetail(
        id = id,
        backdropPath = backdrop_path,
        genres = genres.map { it.name },
        title = title,
        overview = overview,
        posterPath = poster_path,
        voteAverage = vote_average,
        releaseDate = release_date
    )
}

fun CastDto.toFilmCast(): FilmCast {
    return FilmCast(
        cast = cast.map { it.toCast() }
    )
}

fun SearchDto.toSearchList(): FilmList {
    return FilmList(
        films = films.map { it.toFilmData() }
    )
}


fun FilmDataDto.toFilmData(): FilmData {
    return FilmData(
        id = id,
        backdropPath = backdrop_path,
        title = title,
        voteAverage = vote_average,
        releaseDate = release_date,
        genres = genre_ids.map { GenreType.toGenre(it) }
    )
}

fun CastResponseDto.toCast(): Cast {
    return Cast(
        profilePath = profile_path,
        name = name
    )
}

fun FilmDetail.toWatchListEntity(): WatchListEntity {
    return WatchListEntity(
        id = id,
        image = backdropPath,
        title = title,
        rating = voteAverage,
        releaseDate = releaseDate
    )
}

fun WatchListEntity.toFilmDetail(): FilmDetail{
    return FilmDetail(
        id = id!!,
        backdropPath = image,
        title = title,
        voteAverage = rating,
        releaseDate = releaseDate,
        genres = emptyList(),
        posterPath = null,
        overview = ""
    )
}

