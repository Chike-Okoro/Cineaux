package com.ceocoding.cineaux.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CastDto(
    @SerializedName("cast")
    val cast: List<CastResponseDto>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)