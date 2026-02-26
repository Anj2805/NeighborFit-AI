package com.example.neighborfitai.domain.usecase

import com.example.neighborfitai.domain.model.Neighborhood
import com.example.neighborfitai.domain.repository.NeighborhoodRepository

class GetNeighborhoodDetailUseCase(
    private val repository: NeighborhoodRepository
) {
    suspend operator fun invoke(id: String): Neighborhood? {
        return repository.getNeighborhoods().find { it.id == id }
    }
}
