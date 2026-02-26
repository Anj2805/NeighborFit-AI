package com.example.neighborfitai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.neighborfitai.data.local.entity.NeighborhoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NeighborhoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(neighborhoods: List<NeighborhoodEntity>)

    @Query("SELECT * FROM neighborhoods")
    fun getAllNeighborhoods(): Flow<List<NeighborhoodEntity>>

    @Query("SELECT * FROM neighborhoods WHERE id = :id")
    suspend fun getNeighborhoodById(id: String): NeighborhoodEntity?

    @Query("DELETE FROM neighborhoods")
    suspend fun clearAll()
}
