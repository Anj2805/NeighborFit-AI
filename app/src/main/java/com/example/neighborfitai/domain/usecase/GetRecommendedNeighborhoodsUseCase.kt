package com.example.neighborfitai.domain.usecase

import com.example.neighborfitai.domain.model.Neighborhood
import com.example.neighborfitai.domain.model.Preference
import com.example.neighborfitai.domain.repository.NeighborhoodRepository

class GetRecommendedNeighborhoodsUseCase(
    private val repository: NeighborhoodRepository,
    private val calculateMatchScoreUseCase: CalculateMatchScoreUseCase
) {

    suspend operator fun invoke(
        preference: Preference
    ): List<Neighborhood> {
        val neighborhoods = repository.getNeighborhoods()
        
        return neighborhoods.map { neighborhood ->
            val score = calculateMatchScoreUseCase(neighborhood, preference)

            neighborhood.copy(
                matchPercentage = score.matchPercentage,
                aiExplanation = score.explanation
            )
        }.sortedByDescending { it.matchPercentage }
    }
}
