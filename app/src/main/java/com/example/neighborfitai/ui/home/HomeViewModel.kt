package com.example.neighborfitai.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.neighborfitai.data.local.UserPreferenceManager
import com.example.neighborfitai.domain.repository.NeighborhoodRepository
import com.example.neighborfitai.domain.usecase.CalculateMatchScoreUseCase
import com.example.neighborfitai.ui.state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: NeighborhoodRepository,
    private val preferenceManager: UserPreferenceManager,
    private val calculateMatchScoreUseCase: CalculateMatchScoreUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadRecommendations()
    }

    fun loadRecommendations() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                // 1. Load preferences
                val preferences = preferenceManager.userPreferences.first()
                
                // 2. Load neighborhoods from Room (via Repository)
                val neighborhoods = repository.getNeighborhoods()
                
                // 3. Apply Matching Engine & Sort
                val matchedNeighborhoods = neighborhoods.map { neighborhood ->
                    val aiScore = calculateMatchScoreUseCase(neighborhood, preferences)
                    neighborhood.copy(
                        matchPercentage = aiScore.matchPercentage,
                        aiExplanation = aiScore.explanation
                    )
                }.sortedByDescending { it.matchPercentage }

                // 4. Emit UI State
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    neighborhoods = matchedNeighborhoods,
                    error = null
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load recommendations"
                )
            }
        }
    }
}
