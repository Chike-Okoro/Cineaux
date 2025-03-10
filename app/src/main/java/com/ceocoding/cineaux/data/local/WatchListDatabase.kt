package com.ceocoding.cineaux.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ceocoding.cineaux.data.local.entity.WatchListEntity

@Database(
    entities = [WatchListEntity::class],
    version = 1
)
abstract class WatchListDatabase: RoomDatabase() {
    abstract val dao: WatchListDao
}