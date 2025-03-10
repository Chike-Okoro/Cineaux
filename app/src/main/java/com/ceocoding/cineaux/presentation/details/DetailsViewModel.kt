package com.ceocoding.cineaux.presentation.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceocoding.cineaux.domain.repository.FilmRepository
import com.ceocoding.cineaux.util.Constants
import com.ceocoding.cineaux.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: FilmRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(DetailsState())
    val state: State<DetailsState> = _state

    private val _castState = mutableStateOf(DetailsCastState())
    val castState: State<DetailsCastState> = _castState

    init {
        val movieId = savedStateHandle.get<Int>(Constants.PARAM_FILM_ID)!!
        if(movieId != -1){
            getMovieDetails(movieId)
            getFilmCast(movieId)
        }
    }

    private fun getFilmCast(movieId: Int){
        viewModelScope.launch {
            repository.getFilmCast(movieId).onEach { result ->
                when(result){
                    is Resource.Loading -> {
                        _castState.value = DetailsCastState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _castState.value = DetailsCastState(cast = result.data ?: emptyList())
                    }
                    is Resource.Error -> {
                        _castState.value = DetailsCastState(error = result.message ?: "Unknown error")
                    }
                }
            }.launchIn(this)
        }
    }

    private fun getMovieDetails(movieId: Int){
        viewModelScope.launch {
            repository.getFilmDetails(movieId).onEach { result ->
                when(result){
                    is Resource.Loading -> {
                        _state.value = DetailsState(isLoading = true)
                    }
                    is Resource.Success -> {
                        _state.value = DetailsState(filmDetail = result.data)
                    }
                    is Resource.Error -> {
                        _state.value = DetailsState(error = result.message ?: "Unknown Error")
                    }
                }
            }.launchIn(this)
        }
    }
}