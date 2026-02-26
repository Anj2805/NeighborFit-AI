package com.example.neighborfitai.data.repository

import com.example.neighborfitai.data.local.dao.NeighborhoodDao
import com.example.neighborfitai.data.mapper.toDomain
import com.example.neighborfitai.data.mapper.toEntity
import com.example.neighborfitai.domain.model.Neighborhood
import com.example.neighborfitai.domain.repository.NeighborhoodRepository
import kotlinx.coroutines.flow.first

class NeighborhoodRepositoryImpl(
    private val dao: NeighborhoodDao
) : NeighborhoodRepository {

    override suspend fun getNeighborhoods(): List<Neighborhood> {
        val localEntities = dao.getAllNeighborhoods().first()
        return if (localEntities.isEmpty()) {
            val dummyList = getDummyNeighborhoods()
            dao.insertAll(dummyList.map { it.toEntity() })
            dummyList
        } else {
            localEntities.map { it.toDomain() }
        }
    }

    override suspend fun getNeighborhoodById(id: String): Neighborhood? {
        return dao.getNeighborhoodById(id)?.toDomain()
    }

    private fun getDummyNeighborhoods(): List<Neighborhood> {
        return listOf(
            Neighborhood("1", "Student Area", "Metro City", 8000.0, 6.5, 8.5, 5.0, 9.0, "", 0, ""),
            Neighborhood("2", "Family Area", "Metro City", 15000.0, 9.2, 7.5, 9.5, 4.0, "", 0, ""),
            Neighborhood("3", "Corporate Hub", "Metro City", 22000.0, 8.0, 9.5, 7.0, 8.5, "", 0, ""),
            Neighborhood("4", "Budget Colony", "Metro City", 6000.0, 5.5, 6.0, 5.5, 5.0, "", 0, ""),
            Neighborhood("5", "Premium Residency", "Metro City", 35000.0, 9.5, 8.0, 9.0, 7.0, "", 0, ""),
            Neighborhood("6", "Tech Park District", "Metro City", 25000.0, 8.8, 9.2, 7.5, 8.0, "", 0, ""),
            Neighborhood("7", "Green Meadows", "Metro City", 18000.0, 9.0, 6.5, 8.5, 5.0, "", 0, ""),
            Neighborhood("8", "Downtown Central", "Metro City", 28000.0, 7.5, 9.8, 6.0, 9.5, "", 0, ""),
            Neighborhood("9", "Suburban Heights", "Metro City", 12000.0, 8.5, 6.8, 8.0, 4.5, "", 0, ""),
            Neighborhood("10", "Lakeview Enclave", "Metro City", 20000.0, 9.3, 7.2, 8.8, 6.0, "", 0, "")
        )
    }
}
