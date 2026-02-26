package com.example.neighborfitai.data.remote

import com.example.neighborfitai.data.model.AiScoreResponse
import retrofit2.http.POST
import retrofit2.http.Body

interface AiApiService {
    @POST("ai/score")
    suspend fun getAiScore(@Body userPreferences: Map<String, String>): AiScoreResponse
}
