package com.example.neighborfitai.domain.usecase

import com.example.neighborfitai.domain.model.Preference
import com.example.neighborfitai.domain.repository.UserRepository

class SaveUserPreferencesUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(preference: Preference): Boolean {
        return repository.saveUserPreferences(preference)
    }
}
