package com.ceocoding.cineaux.domain.use_case

import com.ceocoding.cineaux.domain.model.FilmData
import com.ceocoding.cineaux.domain.repository.FilmRepository
import com.ceocoding.cineaux.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetFilmData(
    private val repository: FilmRepository
) {
    suspend operator fun invoke(query: String): Flow<Resource<List<FilmData>>>{
        if(query.isBlank()){
            return flow {  }
        }
        return repository.searchMovie(query)
    }
}