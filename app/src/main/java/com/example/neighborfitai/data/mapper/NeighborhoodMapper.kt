package com.example.neighborfitai.data.mapper

import com.example.neighborfitai.data.local.entity.NeighborhoodEntity
import com.example.neighborfitai.domain.model.Neighborhood

fun NeighborhoodEntity.toDomain(): Neighborhood {
    return Neighborhood(
        id = id,
        name = name,
        city = city,
        averageRent = averageRent,
        safetyRating = safetyRating,
        transportScore = transportScore,
        schoolScore = schoolScore,
        nightlifeScore = nightlifeScore,
        imageUrl = imageUrl,
        matchPercentage = 0,
        aiExplanation = ""
    )
}

fun Neighborhood.toEntity(): NeighborhoodEntity {
    return NeighborhoodEntity(
        id = id,
        name = name,
        city = city,
        averageRent = averageRent,
        safetyRating = safetyRating,
        transportScore = transportScore,
        schoolScore = schoolScore,
        nightlifeScore = nightlifeScore,
        imageUrl = imageUrl
    )
}
