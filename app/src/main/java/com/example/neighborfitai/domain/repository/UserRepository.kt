package com.example.neighborfitai.domain.repository

import com.example.neighborfitai.domain.model.User
import com.example.neighborfitai.domain.model.Preference

interface UserRepository {
    suspend fun getUser(): User?
    suspend fun saveUserPreferences(preference: Preference): Boolean
    suspend fun login(email: String, password: String): User?
    suspend fun register(user: User, password: String): User?
}
