package com.example.neighborfitai.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.neighborfitai.data.local.entity.SavedNeighborhoodEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SavedNeighborhoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNeighborhood(savedNeighborhood: SavedNeighborhoodEntity)

    @Query("SELECT * FROM saved_neighborhoods WHERE userId = :userId")
    fun getSavedNeighborhoods(userId: String): Flow<List<SavedNeighborhoodEntity>>

    @Query("DELETE FROM saved_neighborhoods WHERE id = :neighborhoodId AND userId = :userId")
    suspend fun removeSavedNeighborhood(neighborhoodId: String, userId: String)

    @Query("SELECT EXISTS(SELECT 1 FROM saved_neighborhoods WHERE id = :neighborhoodId AND userId = :userId)")
    suspend fun isNeighborhoodSaved(neighborhoodId: String, userId: String): Boolean
}
