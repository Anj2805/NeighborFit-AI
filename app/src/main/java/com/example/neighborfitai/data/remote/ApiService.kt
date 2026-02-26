package com.example.neighborfitai.data.remote

import com.example.neighborfitai.data.model.NeighborhoodDto
import retrofit2.http.GET

interface ApiService {
    @GET("neighborhoods")
    suspend fun getNeighborhoods(): List<NeighborhoodDto>
}
