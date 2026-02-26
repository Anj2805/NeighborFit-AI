package com.example.neighborfitai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_neighborhoods")
data class SavedNeighborhoodEntity(
    @PrimaryKey val id: String,
    val userId: String
)
