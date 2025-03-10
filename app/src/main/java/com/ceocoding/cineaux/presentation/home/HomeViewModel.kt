package com.ceocoding.cineaux.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceocoding.cineaux.domain.repository.FilmRepository
import com.ceocoding.cineaux.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FilmRepository
): ViewModel() {


    private val _trendingFilmsState = mutableStateOf(HomeState())
    val trendingFilmsState: State<HomeState> = _trendingFilmsState

    private val _nowPlayingFilmsState = mutableStateOf(HomeState())
    val nowPlayingFilmsState: State<HomeState> = _nowPlayingFilmsState

    private val _popularFilmsState = mutableStateOf(HomeState())
    val popularFilmsState: State<HomeState> = _popularFilmsState

    private val _upcomingFilmsState = mutableStateOf(HomeState())
    val upcomingFilmsState: State<HomeState> = _upcomingFilmsState

    private val _topRatedFilmsState = mutableStateOf(HomeState())
    val topRatedFilmsState: State<HomeState> = _topRatedFilmsState


    init {
        refreshAll()
    }

    fun refreshAll(){
        getTrendingFilms()
        getNowPlayingFilms()
        getTopRatedFilms()
        getTopRatedFilms()
        getUpcomingFilms()
        getPopularFilms()
    }


    private fun getTrendingFilms(){
        fetchFilmsByType(FilmType.TRENDING)
    }

    private fun getNowPlayingFilms(){
        fetchFilmsByType(FilmType.NOW_PLAYING)
    }

    private fun getTopRatedFilms(){
        fetchFilmsByType(FilmType.TOP_RATED)
    }

    private fun getPopularFilms(){
        fetchFilmsByType(FilmType.POPULAR)
    }

    private fun getUpcomingFilms(){
        fetchFilmsByType(FilmType.UPCOMING)
    }


    private fun fetchFilmsByType(filmType: FilmType){
        viewModelScope.launch {
            when(filmType){
                FilmType.TRENDING -> repository.getTrendingFilms()
                FilmType.NOW_PLAYING -> repository.getNowPlayingFilms()
                FilmType.POPULAR -> repository.getPopularFilms()
                FilmType.TOP_RATED -> repository.getTopRatedFilms()
                FilmType.UPCOMING -> repository.getUpcomingFilms()
            }.onEach { result ->
                when(result){
                    is Resource.Loading -> {
                        updateFilmState(
                            HomeState(
                                films = result.data ?: emptyList(),
                                isLoading = true
                            ), filmType)
                    }
                    is Resource.Success -> {
                        updateFilmState(
                            HomeState(
                                films = result.data ?: emptyList(),
                                isLoading = false
                            ), filmType)
                    }
                    is Resource.Error -> {
                        updateFilmState(
                            HomeState(
                                error = result.message ?: "An unexpected error occurred.\n Tap to Retry"
                        ), filmType)
                    }
                }
            }.launchIn(this)
        }
    }


    private fun updateFilmState(homeState: HomeState, filmType: FilmType){
        when(filmType){
            FilmType.TRENDING -> _trendingFilmsState.value = homeState
            FilmType.NOW_PLAYING -> _nowPlayingFilmsState.value = homeState
            FilmType.POPULAR -> _popularFilmsState.value = homeState
            FilmType.TOP_RATED -> _topRatedFilmsState.value = homeState
            FilmType.UPCOMING -> _upcomingFilmsState.value = homeState
        }
    }

}