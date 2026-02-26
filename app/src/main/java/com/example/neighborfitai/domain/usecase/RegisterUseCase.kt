package com.example.neighborfitai.domain.usecase

import com.example.neighborfitai.domain.model.User
import com.example.neighborfitai.domain.repository.UserRepository

class RegisterUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User, password: String): User? {
        return repository.register(user, password)
    }
}
