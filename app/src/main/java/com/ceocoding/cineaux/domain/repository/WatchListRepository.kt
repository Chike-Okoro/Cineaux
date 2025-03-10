package com.ceocoding.cineaux.domain.repository

import com.ceocoding.cineaux.domain.model.FilmData
import com.ceocoding.cineaux.domain.model.FilmDetail
import kotlinx.coroutines.flow.Flow

interface WatchListRepository {

    suspend fun addToWatchList(filmDetail: FilmDetail)

    suspend fun removeFromWatchList(filmDetail: FilmDetail)

    suspend fun deleteAll()

    fun getFullWatchList(): Flow<List<FilmDetail>>

    suspend fun exists(id: Int): FilmDetail?
}