package com.ceocoding.cineaux.presentation.search

sealed class SearchEvent{
    data class OnQueryChange(val query: String): SearchEvent()
    object OnSearch: SearchEvent()
    data class OnSearchFocusChange(val isFocused: Boolean): SearchEvent()
}
