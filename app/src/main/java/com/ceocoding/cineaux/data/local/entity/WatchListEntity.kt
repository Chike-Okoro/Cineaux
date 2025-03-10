package com.ceocoding.cineaux.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class WatchListEntity(
    val image: String?,
    val title: String,
    val rating: Double,
    val releaseDate: String,
    @PrimaryKey val id: Int? = null
)
