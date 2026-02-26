package com.example.neighborfitai.data.model

data class AiScoreResponse(
    val neighborhoodId: String,
    val matchPercentage: Int,
    val explanation: String
)
