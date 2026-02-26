package com.example.neighborfitai.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.neighborfitai.data.local.entity.NeighborhoodEntity

@Dao
interface NeighborhoodDao {

    @Query("SELECT * FROM neighborhoods")
    suspend fun getAllNeighborhoods(): List<NeighborhoodEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNeighborhoods(neighborhoods: List<NeighborhoodEntity>)
}
