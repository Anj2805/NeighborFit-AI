package com.example.neighborfitai.data.model

data class PreferenceDto(
    val userId: String,
    val preferredCategories: List<String>,
    val budgetRange: String,
    val commutePreference: String
)
