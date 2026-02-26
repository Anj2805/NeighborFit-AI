package com.example.neighborfitai.domain.repository

import com.example.neighborfitai.domain.model.Neighborhood

interface NeighborhoodRepository {
    suspend fun getNeighborhoods(): List<Neighborhood>
    suspend fun getNeighborhoodById(id: String): Neighborhood?
}
