package com.example.neighborfitai.domain.model

data class Neighborhood(
    val id: String,
    val name: String,
    val city: String,
    val averageRent: Double,
    val safetyRating: Double,
    val transportScore: Double,
    val schoolScore: Double,
    val nightlifeScore: Double,
    val imageUrl: String,
    val matchPercentage: Int,
    val aiExplanation: String,
    val latitude: Double,
    val longitude: Double
)
