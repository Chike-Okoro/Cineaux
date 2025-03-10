package com.ceocoding.cineaux.data.repository

import com.ceocoding.cineaux.data.mappers.toFilmCast
import com.ceocoding.cineaux.data.mappers.toFilmDetail
import com.ceocoding.cineaux.data.mappers.toFilmList
import com.ceocoding.cineaux.data.mappers.toSearchList
import com.ceocoding.cineaux.data.remote.ApiService
import com.ceocoding.cineaux.domain.model.*
import com.ceocoding.cineaux.domain.repository.FilmRepository
import com.ceocoding.cineaux.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val api: ApiService
): FilmRepository {
    override suspend fun getTrendingFilms(): Flow<Resource<List<FilmData>>> = flow {
        try {
            emit(Resource.Loading())
            val remoteFilmData = api.getTrendingFilms().toFilmList()
            emit(Resource.Success(remoteFilmData.films))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong.\n Tap to Retry"
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.\n Tap to Retry"
            ))
        }
    }

    override suspend fun getNowPlayingFilms(): Flow<Resource<List<FilmData>>> = flow{
        try {
            emit(Resource.Loading())
            val remoteFilmData = api.getNowPlayingFilms().toFilmList()
            emit(Resource.Success(remoteFilmData.films))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong.\n" +
                        "Tap to Retry"
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.\n" +
                         "Tap to Retry"
            ))
        }
    }

    override suspend fun getPopularFilms(): Flow<Resource<List<FilmData>>> = flow {
        try {
            emit(Resource.Loading())
            val remoteFilmData = api.getPopularFilms().toFilmList()
            emit(Resource.Success(remoteFilmData.films))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong.\n" +
                        "Tap to Retry"
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.\n" +
                        "Tap to Retry"
            ))
        }
    }

    override suspend fun getTopRatedFilms(): Flow<Resource<List<FilmData>>> = flow{
        try {
            emit(Resource.Loading())
            val remoteFilmData = api.getTopRatedFilms().toFilmList()
            emit(Resource.Success(remoteFilmData.films))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong.\n" +
                        "Tap to Retry"
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.\n" +
                        "Tap to Retry"
            ))
        }
    }

    override suspend fun getUpcomingFilms(): Flow<Resource<List<FilmData>>> = flow {
        try {
            emit(Resource.Loading())
            val remoteFilmData = api.getUpcomingFilms().toFilmList()
            emit(Resource.Success(remoteFilmData.films))
        } catch (e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong.\n" +
                        "Tap to Retry"
            ))
        } catch (e: IOException){
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.\n" +
                        "Tap to Retry"
            ))
        }
    }

    override suspend fun getFilmDetails(movieId: Int): Flow<Resource<FilmDetail>> {
        return flow {
            try {
                emit(Resource.Loading())
                val films = api.getFilmDetails(movieId).toFilmDetail()
                emit(Resource.Success(films))
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException){
                emit(Resource.Error("Couldn't reach server. Check your connection"))
            }
        }
    }

    override suspend fun getFilmCast(movieId: Int): Flow<Resource<List<Cast>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val films = api.getFilmCast(movieId).toFilmCast()
                emit(Resource.Success(films.cast))
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException){
                emit(Resource.Error("Couldn't reach server. Check your connection"))
            }
        }
    }

    override suspend fun searchMovie(query: String): Flow<Resource<List<FilmData>>> {
        return flow {
            try {
                emit(Resource.Loading())
                val remoteFilmData = api.searchFilms(query = query).toSearchList()
                emit(Resource.Success(remoteFilmData.films))
            }catch (e: HttpException){
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            }catch (e: IOException){
                emit(Resource.Error("Couldn't reach server. Check your connection"))
            }
        }
    }
}