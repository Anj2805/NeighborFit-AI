package com.example.neighborfitai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.neighborfitai.data.local.dao.NeighborhoodDao
import com.example.neighborfitai.data.local.dao.SavedNeighborhoodDao
import com.example.neighborfitai.data.local.entity.NeighborhoodEntity
import com.example.neighborfitai.data.local.entity.SavedNeighborhoodEntity

@Database(
    entities = [NeighborhoodEntity::class, SavedNeighborhoodEntity::class],
    version = 2,
    exportSchema = false
)
abstract class NeighborFitDatabase : RoomDatabase() {

    abstract fun neighborhoodDao(): NeighborhoodDao
    abstract fun savedNeighborhoodDao(): SavedNeighborhoodDao
}
