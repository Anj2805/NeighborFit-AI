package com.example.neighborfitai.data.repository

import com.example.neighborfitai.data.local.UserPreferenceManager
import com.example.neighborfitai.domain.model.Preference
import com.example.neighborfitai.domain.model.User
import com.example.neighborfitai.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth

class UserRepositoryImpl(
    private val preferenceManager: UserPreferenceManager
) : UserRepository {

    private val auth = FirebaseAuth.getInstance()

    override suspend fun getUser(): User? {
        val firebaseUser = auth.currentUser
        return firebaseUser?.let {
            User(it.uid, it.displayName ?: "", it.email ?: "")
        }
    }

    override suspend fun saveUserPreferences(preference: Preference): Boolean {
        return try {
            preferenceManager.savePreferences(preference)
            true
        } catch (e: Exception) {
            false
        }
    }

    override suspend fun login(email: String, password: String): User? = null
    override suspend fun register(user: User, password: String): User? = null
}
