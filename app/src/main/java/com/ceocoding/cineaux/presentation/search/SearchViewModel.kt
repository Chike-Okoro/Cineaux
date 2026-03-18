package com.ceocoding.cineaux.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ceocoding.cineaux.R
import com.ceocoding.cineaux.domain.use_case.GetFilmData
import com.ceocoding.cineaux.util.Resource
import com.ceocoding.cineaux.util.UiEvent
import com.ceocoding.cineaux.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFilmData: GetFilmData
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()



    fun onEvent(event: SearchEvent){
        when(event){
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
        }
    }

    private fun executeSearch(){
        if(state.query.isBlank()) return
        viewModelScope.launch {
            getFilmData(state.query)
                .onEach { result ->
                    when(result){
                        is Resource.Loading -> {
                            state = state.copy(isSearching = true, hasSearched = true)
                        }
                        is Resource.Error -> {
                            state = state.copy(isSearching = false, hasSearched = true)
                            _uiEvent.send(
                                UiEvent.ShowToast(
                                    UiText.StringResource(R.string.error_something_went_wrong)
                                )
                            )
                        }
                        is Resource.Success -> {
                            state = state.copy(
                                films = result.data ?: emptyList(),
                                isSearching = false,
                                hasSearched = true
                            )
                        }
                    }
                }.launchIn(this)
        }

    }
}