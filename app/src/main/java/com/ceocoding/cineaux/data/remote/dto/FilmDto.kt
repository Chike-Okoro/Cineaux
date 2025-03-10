package com.ceocoding.cineaux.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FilmDto(
    @SerializedName("dates")
    val dates: Dates,
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val films: List<FilmDataDto>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)