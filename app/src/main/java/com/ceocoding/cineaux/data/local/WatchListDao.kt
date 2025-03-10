package com.ceocoding.cineaux.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ceocoding.cineaux.data.local.entity.WatchListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WatchListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWatchList(watchListEntity: WatchListEntity)

    @Delete
    suspend fun removeFromWatchList(watchListEntity: WatchListEntity)

    @Query("DELETE FROM watchlistentity")
    suspend fun deleteAll()

    @Query("SELECT * FROM watchlistentity WHERE id = :id")
    suspend fun exists(id: Int): WatchListEntity?

    @Query("SELECT * FROM watchlistentity")
    fun getFullWatchList(): Flow<List<WatchListEntity>>

}
