package com.example.neighborfitai.data.model

data class NeighborhoodDto(
    val id: String,
    val name: String,
    val city: String,
    val averageRent: Double,
    val safetyRating: Double,
    val transportScore: Double,
    val schoolScore: Double,
    val nightlifeScore: Double,
    val imageUrl: String
)
