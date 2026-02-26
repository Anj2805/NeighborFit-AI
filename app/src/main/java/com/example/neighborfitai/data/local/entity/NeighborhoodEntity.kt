package com.example.neighborfitai.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "neighborhoods")
data class NeighborhoodEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val city: String,
    val averageRent: Double,
    val safetyRating: Double,
    val transportScore: Double,
    val schoolScore: Double,
    val nightlifeScore: Double,
    val imageUrl: String,
    val latitude: Double,
    val longitude: Double
)
