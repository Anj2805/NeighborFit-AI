package com.example.neighborfitai.domain.model

data class AiScore(
    val neighborhoodId: String,
    val matchPercentage: Int,
    val explanation: String
)
