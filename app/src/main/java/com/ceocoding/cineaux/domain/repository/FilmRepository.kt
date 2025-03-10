package com.ceocoding.cineaux.domain.repository

import com.ceocoding.cineaux.domain.model.Cast
import com.ceocoding.cineaux.domain.model.FilmData
import com.ceocoding.cineaux.domain.model.FilmDetail
import com.ceocoding.cineaux.domain.model.FilmList
import com.ceocoding.cineaux.util.Resource
import kotlinx.coroutines.flow.Flow

interface FilmRepository {

    suspend fun getTrendingFilms(): Flow<Resource<List<FilmData>>>

    suspend fun getNowPlayingFilms(): Flow<Resource<List<FilmData>>>

    suspend fun getPopularFilms(): Flow<Resource<List<FilmData>>>

    suspend fun getTopRatedFilms(): Flow<Resource<List<FilmData>>>

    suspend fun getUpcomingFilms(): Flow<Resource<List<FilmData>>>

    suspend fun getFilmDetails(movieId: Int): Flow<Resource<FilmDetail>>

    suspend fun getFilmCast(movieId: Int): Flow<Resource<List<Cast>>>

    suspend fun searchMovie(query: String): Flow<Resource<List<FilmData>>>
}