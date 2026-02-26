package com.example.neighborfitai.domain.usecase

import com.example.neighborfitai.domain.model.User
import com.example.neighborfitai.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(email: String, password: String): User? {
        return repository.login(email, password)
    }
}
