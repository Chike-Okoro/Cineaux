package com.ceocoding.cineaux.presentation.watch_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceocoding.cineaux.domain.model.FilmDetail
import com.ceocoding.cineaux.domain.repository.WatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val repository: WatchListRepository
): ViewModel(){

    private val isInWatchListMap = mutableMapOf<Int, MutableState<Boolean>>()

    val fullWatchList = repository.getFullWatchList()

    init {
        checkIfInWatchList()
    }

    fun getOrCreateStateForFilm(id: Int): MutableState<Boolean>{
        return isInWatchListMap.getOrPut(id){
            mutableStateOf(false)
        }
    }

    fun addToWatchList(filmDetail: FilmDetail){
        viewModelScope.launch {
            val exists = repository.exists(filmDetail.id)
            if(exists == null){
                repository.addToWatchList(filmDetail)
            }
            getOrCreateStateForFilm(filmDetail.id).value = true
        }
    }

    private fun checkIfInWatchList() {
        viewModelScope.launch {
            val allWatchListItems = repository.getFullWatchList()
            allWatchListItems.onEach { filmDetails ->
                filmDetails.forEach { filmDetail ->
                    val exists = repository.exists(filmDetail.id)
                    getOrCreateStateForFilm(filmDetail.id).value = exists != null
                }
            }.launchIn(this)
        }
    }

    fun removeFromWatchList(filmDetail: FilmDetail){
        viewModelScope.launch {
            val exists = repository.exists(filmDetail.id)
            if(exists != null){
                repository.removeFromWatchList(filmDetail)
                getOrCreateStateForFilm(filmDetail.id).value = false
            }
        }
    }

    fun deleteAll(){
        viewModelScope.launch{
            repository.deleteAll()
            isInWatchListMap.clear()
        }
    }
}