package com.example.neighborfitai.domain.usecase

import com.example.neighborfitai.domain.model.Neighborhood
import com.example.neighborfitai.domain.model.Preference
import com.example.neighborfitai.domain.model.AiScore
import kotlin.math.roundToInt

class CalculateMatchScoreUseCase {

    operator fun invoke(
        neighborhood: Neighborhood,
        preference: Preference
    ): AiScore {

        // Normalize weights
        val totalWeight = preference.safetyWeight +
                preference.commuteWeight +
                preference.schoolWeight +
                preference.nightlifeWeight

        if (totalWeight == 0) {
            return AiScore(
                neighborhoodId = neighborhood.id,
                matchPercentage = 0,
                explanation = "No preferences selected."
            )
        }

        // Budget Score (0 or 100)
        val budgetScore = if (neighborhood.averageRent <= preference.maxBudget) {
            100.0
        } else {
            0.0
        }

        // Weighted Feature Score
        // Assuming neighborhood ratings are out of 10
        val featureScore =
            (neighborhood.safetyRating * preference.safetyWeight) +
            (neighborhood.transportScore * preference.commuteWeight) +
            (neighborhood.schoolScore * preference.schoolWeight) +
            (neighborhood.nightlifeScore * preference.nightlifeWeight)

        // featureScore is sum of (rating * weight). 
        // Max possible featureScore = 10 * totalWeight (if all ratings are 10)
        // To get a 0-100 scale: (featureScore / (10 * totalWeight)) * 100
        val normalizedFeatureScore = (featureScore / (10.0 * totalWeight)) * 100.0

        // Final Score Calculation (70% features + 30% budget)
        val finalScore =
            (normalizedFeatureScore * 0.7) +
            (budgetScore * 0.3)

        val matchPercentage = finalScore.coerceIn(0.0, 100.0).roundToInt()

        val explanation = generateExplanation(neighborhood, preference, matchPercentage)

        return AiScore(
            neighborhoodId = neighborhood.id,
            matchPercentage = matchPercentage,
            explanation = explanation
        )
    }

    private fun generateExplanation(
        neighborhood: Neighborhood,
        preference: Preference,
        score: Int
    ): String {

        val reasons = mutableListOf<String>()

        if (neighborhood.averageRent <= preference.maxBudget) {
            reasons.add("fits within your budget")
        }

        if (preference.safetyWeight >= 4 && neighborhood.safetyRating >= 8) {
            reasons.add("offers excellent safety")
        }

        if (preference.commuteWeight >= 4 && neighborhood.transportScore >= 8) {
            reasons.add("has strong transport connectivity")
        }

        if (preference.schoolWeight >= 4 && neighborhood.schoolScore >= 8) {
            reasons.add("has high-rated schools")
        }

        if (preference.nightlifeWeight >= 4 && neighborhood.nightlifeScore >= 8) {
            reasons.add("has vibrant nightlife")
        }

        return if (reasons.isEmpty()) {
            "This neighborhood moderately matches your preferences with a score of $score%."
        } else {
            "This neighborhood matches your preferences because it ${reasons.joinToString(", ")} (Score: $score%)."
        }
    }
}
