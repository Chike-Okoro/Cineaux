package com.ceocoding.cineaux.data.repository

import com.ceocoding.cineaux.data.local.WatchListDao
import com.ceocoding.cineaux.data.mappers.toFilmData
import com.ceocoding.cineaux.data.mappers.toFilmDetail
import com.ceocoding.cineaux.data.mappers.toWatchListEntity
import com.ceocoding.cineaux.domain.model.FilmData
import com.ceocoding.cineaux.domain.model.FilmDetail
import com.ceocoding.cineaux.domain.repository.WatchListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WatchListRepositoryImpl @Inject constructor(
    private val dao: WatchListDao
): WatchListRepository{
    override suspend fun addToWatchList(filmDetail: FilmDetail) {
        return dao.addToWatchList(filmDetail.toWatchListEntity())
    }

    override suspend fun removeFromWatchList(filmDetail: FilmDetail) {
        return dao.removeFromWatchList(filmDetail.toWatchListEntity())
    }

    override suspend fun deleteAll() {
        return dao.deleteAll()
    }

    override fun getFullWatchList(): Flow<List<FilmDetail>> {
        return dao.getFullWatchList().map { entities ->
            entities.map { it.toFilmDetail() }
        }
    }

    override suspend fun exists(id: Int): FilmDetail? {
        return dao.exists(id)?.toFilmDetail()
    }
}