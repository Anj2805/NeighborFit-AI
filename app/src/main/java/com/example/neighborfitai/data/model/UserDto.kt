package com.example.neighborfitai.data.model

data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val preferences: List<String>
)
