package com.ceocoding.cineaux.data.remote

import com.ceocoding.cineaux.BuildConfig
import com.ceocoding.cineaux.data.remote.dto.CastDto
import com.ceocoding.cineaux.data.remote.dto.FilmDetailDto
import com.ceocoding.cineaux.data.remote.dto.FilmDto
import com.ceocoding.cineaux.data.remote.dto.SearchDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("trending/movie/day")
    suspend fun getTrendingFilms(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FilmDto

    @GET("movie/now_playing")
    suspend fun getNowPlayingFilms(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FilmDto

    @GET("movie/popular")
    suspend fun getPopularFilms(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FilmDto

    @GET("movie/top_rated")
    suspend fun getTopRatedFilms(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FilmDto

    @GET("movie/upcoming")
    suspend fun getUpcomingFilms(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FilmDto

    @GET("movie/{movie_id}")
    suspend fun getFilmDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): FilmDetailDto

    @GET("movie/{movie_id}/credits")
    suspend fun getFilmCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): CastDto

    @GET("search/movie")
    suspend fun searchFilms(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("query") query: String
    ): SearchDto
}