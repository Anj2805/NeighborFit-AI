package com.example.neighborfitai.ui.state

import com.example.neighborfitai.domain.model.Neighborhood

data class HomeUiState(
    val isLoading: Boolean = false,
    val neighborhoods: List<Neighborhood> = emptyList(),
    val error: String? = null
)
