package com.example.neighborfitai.domain.model

data class Preference(
    val maxBudget: Double,
    val preferredCity: String,
    val safetyWeight: Int,
    val commuteWeight: Int,
    val schoolWeight: Int,
    val nightlifeWeight: Int
)
